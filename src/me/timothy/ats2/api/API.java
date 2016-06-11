package me.timothy.ats2.api;

import me.timothy.ats2.autorestart.AutoRestart;
import me.timothy.ats2.autorestart.lib.AutoRestartlib;
import me.timothy.ats2.playerinfo.GPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by CyanThunderMC on 6/8/2016.
 */
public class API {
    public static class player {
        public static ChatColor getPlayerColor(Player p) {
            return GPlayer.getChatColor(p);
        }

        public static boolean isInterruptible(Player p) {
            return GPlayer.isInterruptable(p);
        }

        public static Interruptible[] getInterruptibles(Player p) {
            return GPlayer.getInterruptibles(p);
        }

        public static void interruptAll(Player p) {
            GPlayer.interruptEvents(p, true);
        }

        public static void interrupt(Player p, Interruptible i) {
            GPlayer.interruptEvent(p, i);
        }
    }

    public static class autorestart {
        public static void setAutoRestartDelay(int[] delay) {
            //[HH, MM, SS]!
            if (delay.length != 3)
                throw new IllegalArgumentException("Argument 'delay' is invalid!");

            AutoRestart.setAutoRestartDelay(delay);
        }

        public static void setWarnHours(int[] hours) {
            AutoRestart.setWarningHours(hours);
        }

        public static void setWarnMinutes(int[] minutes) {
            AutoRestart.setWarningMinutes(minutes);
        }

        public static int[] getAutoRestartDelay() {
            return AutoRestart.getAutoRestartDelay();
        }

        public static int[] getWarnHours() {
            return AutoRestart.getWarningHours();
        }

        public static int[] getWarnMinutes() {
            return AutoRestart.getWarningMinutes();
        }

        public static void stop() {
            AutoRestart.stop();
        }

        public static void run() {
            AutoRestart.run();
        }

        public static void restartServer() {
            AutoRestart.restart();
        }
    }
}
