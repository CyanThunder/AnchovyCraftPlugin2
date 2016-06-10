package me.timothy.ats2.eventhandlers;

import me.timothy.ats2.playerinfo.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
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
}
