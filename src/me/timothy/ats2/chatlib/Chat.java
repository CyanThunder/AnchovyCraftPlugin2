package me.timothy.ats2.chatlib;

import me.timothy.ats2.lib.Reference;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by CyanThunderMC on 6/1/2016.
 */
public class Chat {
    public static void opBroadcast(String msg) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isOp()) {
                player.sendMessage(msg);
            }
        }
        ConsoleChat.log(msg);
    }

    public static void PluginBroadcast(String msg) {
        Bukkit.broadcastMessage(Reference.displayName + " " + msg);
    }
}