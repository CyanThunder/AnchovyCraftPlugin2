package me.timothy.ats2.events.eventhandlers;

import me.timothy.ats2.events.custom.TeleportEvent;
import me.timothy.ats2.events.eventhandlers.lib.BasicEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by CyanThunderMC on 6/20/2016.
 */
public class CustomEventHandler extends BasicEventHandler {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTeleport(TeleportEvent event) {
        if (!event.isCancelled()) {
            if (event.getPlayer().isOnline() && event.getTarget().isOnline())
            event.getPlayer().teleport(event.getTarget().getLocation(), event.getCause());
        }
    }
}
