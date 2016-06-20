package me.timothy.ats2.events.eventhandlers;

import me.timothy.ats2.api.Interruptible;
import me.timothy.ats2.events.eventhandlers.lib.BasicEventHandler;
import me.timothy.ats2.playerinfo.GPlayer;
import me.timothy.ats2.playerinfo.PlayerData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by CyanThunderMC on 6/1/2016.
 */
public class PlayerEventHandler extends BasicEventHandler {

    //Player Join Event
    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        PlayerData pData = new PlayerData(p);
        String PlayerName = p.getName();
    }

    //Player Leave(Quit) Event
    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent event) {

    }

    //Movement Event
    @EventHandler(priority = EventPriority.NORMAL)
    public void onMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();

        //Interrupt Movement Events!
        if (event.getFrom().getBlock() != event.getPlayer().getLocation().getBlock()) {
            Interruptible[] interruptibles = GPlayer.getInterruptibles(p);
            for (Interruptible interruptible : interruptibles) {
                if (interruptible == Interruptible.TELEPORT) {
                    p.sendMessage(ChatColor.GOLD + "[Teleport] " + ChatColor.WHITE + "Interrupted.");
                    GPlayer.interruptEvent(p, Interruptible.TELEPORT);
                }
            }
        }
    }
}
