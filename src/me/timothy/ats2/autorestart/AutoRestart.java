package me.timothy.ats2.autorestart;

import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.autorestart.lib.AutoRestartlib;
import me.timothy.ats2.chatlib.Chat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by CyanThunderMC on 6/11/2016.
 */
public class AutoRestart extends AutoRestartlib {
    private static ATSPlugin plugin = ATSPlugin.getInstance();
    public static void run() {
        //Stop Previous Tasks!
        stop();

        //Auto-Restart code:
        Long[] hSeconds = getWarningHoursInSeconds();
        Long[] iSeconds = getWarningMinutesInSeconds();

        for (long l : hSeconds) {
            String type = "hours";
            if (l/60/60 == 1)
                type = "hour";
            warn((int) (l/60/60), type, getARDSecs() - l);
        }

        for (long l : iSeconds) {
            String type = "minutes";
            if (l/60 == 1)
                type = "minute";
            warn((int) (l/60), type, getARDSecs() - l);
        }

        BukkitTask restartTask = plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                restart();
            }
        }, getARDSecs());

        addTask(restartTask);
    }

    public static void stop() {
        stopTasks();
    }

    public static void restart() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(ChatColor.GRAY + "[" + ChatColor.RED + "ATSPlugin" + ChatColor.AQUA + " : " + ChatColor.RED + "Auto-Reboot" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Server Restarting!");
        }
        plugin.getServer().shutdown();
    }

    public static void warn(int time, String measurement, long delayInSeconds) {
        BukkitTask task = plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                Chat.PluginBroadcast("Server restarting in " + ChatColor.RED + time + ChatColor.YELLOW + measurement + ".");
            }
        }, delayInSeconds);

        addTask(task);
    }
}
