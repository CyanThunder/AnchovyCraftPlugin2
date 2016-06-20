package me.timothy.ats2.playerinfo.lib;

import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.events.custom.TeleportEvent;
import me.timothy.ats2.playerinfo.GPlayer;
import me.timothy.ats2.playerinfo.PlayerData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by CyanThunderMC on 6/20/2016.
 */
public class PlayerTeleport {
    ATSPlugin plugin = ATSPlugin.getInstance();
    private Player player;
    private Player target;

    public PlayerTeleport(Player player, Player target) {
        this.player = player;
        this.target = target;
    }

    public Player getPlayer() {
        return player;
    }

    public TeleportEvent createEvent(PlayerTeleportEvent.TeleportCause cause) {
        TeleportEvent event = new TeleportEvent(player, target, cause);
        return event;
    }

    public boolean warn() {
        if ((new PlayerData(target).getBoolean(PlayerConfig.GIVE_TELEPORT_WARNING))) {
            target.sendMessage(GPlayer.getChatColor(player) + player.getName() + ChatColor.WHITE + " is teleporting to you!");
            return true;
        }
        return false;
    }

    public boolean isValid() {
        //Add whitelist check.

        if (!target.isOnline())
            return false;

        PlayerData targetData = new PlayerData(target);
        if (!targetData.getBoolean(PlayerConfig.ALLOW_TELEPORT))
            return false;
        //ADD WHITELIST SITUATION HERE.
        //CHECK IF PLAYER IS WHITELISTED:


        if (!targetData.getBoolean(PlayerConfig.ALLOW_ALL_TELEPORT))
            return false;

        return false;
    }

    public void callEvent(TeleportEvent event) {
        plugin.getServer().getPluginManager().callEvent(event);
    }
}
