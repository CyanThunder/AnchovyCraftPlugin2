package me.timothy.ats2.events.custom;

import me.timothy.ats2.events.custom.lib.CancellableCustomEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by CyanThunderMC on 6/20/2016.
 */
public class TeleportEvent extends CancellableCustomEvent {
    private final String eventName = "ACP-TELEPORTEVENT";

    private Player player;
    private Player target;

    private PlayerTeleportEvent.TeleportCause cause;

    public TeleportEvent(Player sender, Player target, PlayerTeleportEvent.TeleportCause cause) {
        super("");
        this.player = sender;
        this.target = target;
        this.cause = cause;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getTarget() {
        return target;
    }

    public PlayerTeleportEvent.TeleportCause getCause() {
        return cause;
    }
}
