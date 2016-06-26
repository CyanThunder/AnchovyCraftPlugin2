package me.timothy.ats2.chatlib.antispam.lib;

import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.chatlib.antispam.AntiSpam;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by CyanThunderMC on 6/23/2016.
 */
public class ChatTimeout {
    private ATSPlugin plugin = ATSPlugin.getInstance();

    private Player player;
    private Integer tickDuration;
    private BukkitTask task;

    public Player getPlayer() {
        return player;
    }

    public Integer getOriginalTickDuration() {
        return tickDuration;
    }

    public ChatTimeout(Player player, Integer tickDuration) {
        this.player = player;
        this.tickDuration = tickDuration;
    }

    public BukkitTask getTask() {
        if (task != null)
            return task;
        throw new NullPointerException("ACP AntiSpam -- Task is null.");
    }

    public ChatTimeout timeout() {
        task = plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                AntiSpam.timedOut.remove(this);
            }
        }, getOriginalTickDuration());
        return this;
    }

    public void cancel() {
        getTask().cancel();
        AntiSpam.timedOut.remove(this);
    }
}
