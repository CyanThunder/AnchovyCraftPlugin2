package me.timothy.ats2.chatlib.antispam.lib;

import org.bukkit.entity.Player;

/**
 * Created by CyanThunderMC on 6/24/2016.
 */
public class SpamTrigger {
    private Player player;
    private SpamReason reason;
    private Boolean triggered;

    public SpamTrigger(Player player, SpamReason reason, Boolean triggered) {
        this.player = player;
        this.reason = reason;
        this.triggered = triggered;
    }

    public Player getPlayer() {
        return player;
    }

    public SpamReason getReason() {
        return reason;
    }

    public Boolean getTriggered() {
        return triggered;
    }
}
