package me.timothy.ats2.commands;

import me.timothy.ats2.api.API;
import me.timothy.ats2.api.Interruptible;
import me.timothy.ats2.commands.lib.ACPCommand;
import me.timothy.ats2.events.custom.TeleportEvent;
import me.timothy.ats2.playerinfo.GPlayer;
import me.timothy.ats2.playerinfo.PlayerData;
import me.timothy.ats2.playerinfo.lib.PlayerTeleport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by CyanThunderMC on 6/20/2016.
 */
public class CommandTeleport extends ACPCommand {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            String[] args = strings;
            if (args.length == 1) {
                //Check for Target Validity.
                String str_target = args[0];
                Player target = Bukkit.getPlayer(str_target);
                if (target != null) {
                    //Target Valid!
                    //Check for Teleport Validity.
                    PlayerTeleport pt = new PlayerTeleport(player, target);
                    if (pt.isValid()) {
                        //Interrupt other interruptibles
                        GPlayer.interruptEvent(player, Interruptible.TELEPORT);

                        //WARN TARGET IS APPLICABLE!
                        pt.warn();

                        BukkitTask task = plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                pt.callEvent(pt.createEvent(PlayerTeleportEvent.TeleportCause.COMMAND));
                            }
                        }, 5*20L);

                        GPlayer.teleportTask.put(player, task);
                    } else {
                        player.getPlayer().sendMessage("You do not have permission to teleport to " + GPlayer.getChatColor(target) + target.getName() + ChatColor.WHITE + ".");
                    }
                } else {
                    player.sendMessage("Target Player could not be found!");
                }
            }
        } else {
            commandSender.sendMessage("You must be a Player to use this command.");
        }

        return super.onCommand(commandSender, command, s, strings);
    }
}
