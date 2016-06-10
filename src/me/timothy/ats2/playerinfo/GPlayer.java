package me.timothy.ats2.playerinfo;

import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.api.Interruptible;
import me.timothy.ats2.playerinfo.lib.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

/**
 * Created by CyanThunderMC on 5/31/2016.
 */
public class GPlayer {
    private static ATSPlugin plugin = ATSPlugin.getInstance();

    //Teleportation Tasks
    public static Map<Player, BukkitTask> teleportTask = new LinkedHashMap<>();

    //Teleportation Tasks : Functions
    //Interrupt Teleport Event
    public static void interruptTeleportTask(Player p) {
        if (teleportTask.containsKey(p)) {
            //Interrupt/Stop Event
            teleportTask.get(p).cancel();
            teleportTask.remove(p);
        }
    }

    //Inventory
    public static List<Player> refuseAccess = new ArrayList<>();

    //Chat
    public static Map<Player, String> lastMessage = new LinkedHashMap<>();
    public static Map<Player, Integer> lastMessage_repeatCount = new LinkedHashMap<>();
    // ::PLAYER COLOR:: FUNCTION
    public static ChatColor getChatColor(Player p) {
        //Get Config Selected Color:
        PlayerData playerData = new PlayerData(p);
        String color = playerData.getString(PlayerConfig.CHAT_COLOR);
        if (color.length() >= 1) {
            for (ChatColor chatColor : ChatColor.values()) {
                if (chatColor.name().equalsIgnoreCase(color)) {
                    return chatColor;
                }
            }
        }
        playerData.set(PlayerConfig.CHAT_COLOR, "white");
        return ChatColor.WHITE;
    }

    //Chat FUNCTIONS
    public static void opBroadcast(String message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.isOp()) {
                p.sendMessage(message);
            }
        }
        System.out.println(message);
    }

    //Functions
    public static void resetAll() {
        teleportTask.clear();
        refuseAccess.clear();
        lastMessage.clear();
        lastMessage_repeatCount.clear();
    }

    public static int getPlayerCount() {
        return Bukkit.getOnlinePlayers().size();
    }

    //Player Specific Functions
    public static void resetPlayer(Player p) {
        interruptEvents(p);
        refuseAccess.remove(p);
        lastMessage.remove(p);
        lastMessage_repeatCount.remove(p);
    }

    //Interrupt Functions
    public static void interruptEvents(Player p) {
        interruptTeleportTask(p);
    }

    public static void interruptEvent(Player p, Interruptible i) {
        switch (i) {
            case ALL:
                interruptEvents(p);
                break;
            case TELEPORT:
                interruptTeleportTask(p);
                break;
        }
    }
}
