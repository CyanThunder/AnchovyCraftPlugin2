package me.timothy.ats2.api;

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
}
