package me.timothy.ats2.chatlib.antispam.lib;

import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.chatlib.antispam.AntiSpam;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by CyanThunderMC on 6/23/2016.
 */

public class ChatMessage {
    private ATSPlugin plugin = ATSPlugin.getInstance();

    private Player player;
    private String message;
    private Integer tickExpiration;
    private Boolean isExpiring = false;
    private Boolean expired = false;
    private BukkitTask expireTask;

    private SpamReason type;

    public ChatMessage(Player player, String message, Integer duration, SpamReason type) {
        this.player = player;
        this.message = message;
        this.tickExpiration = duration;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public Integer getTickExpiration() {
        return tickExpiration;
    }

    public ChatMessage startExpiration() {
        if (isExpired())
            return this;

        if (isExpiring())
            return this;

        expireTask = plugin.getServer().getScheduler().runTaskLater(plugin, this::expire, tickExpiration);

        isExpiring = true;

        return this;
    }

    public Boolean isExpiring() {
        return isExpiring;
    }

    public Boolean isExpired() {
        return expired;
    }

    public void expire() {
        if (expireTask != null)
            expireTask.cancel();

        if (type == SpamReason.REPEATED_MESSAGE)
            AntiSpam.recentMessages.remove(this);
        if (type == SpamReason.TOO_FAST_MESSAGE)
            AntiSpam.chatCooldown.remove(this);

        isExpiring = false;
        expired = true;
    }
}
