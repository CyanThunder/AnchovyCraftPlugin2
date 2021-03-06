package me.timothy.ats2.playerinfo;

import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.api.Interruptible;
import me.timothy.ats2.chatlib.Chat;
import me.timothy.ats2.lib.Reference;
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

    //Permits:
    public static List<Player> permits = new ArrayList<>();

    public static void permit(Player p) {
        permits.add(p);
    }

    public static boolean hasPermit(Player p) {
        return permits.contains(p);
    }

    public static void removePermit(Player p) {
        if (hasPermit(p))
            permits.remove(p);
    }

    //Teleportation Tasks
    public static Map<Player, BukkitTask> teleportTask = new LinkedHashMap<>();

    public static boolean isTeleporting(Player p) {
        return teleportTask.containsKey(p);
    }

    //Teleportation Tasks : Functions
    //Interrupt Teleport Event
    public static void interruptTeleportTask(Player p) {
        if (isTeleporting(p)) {
            //Interrupt/Stop Event
            teleportTask.get(p).cancel();
            teleportTask.remove(p);
        }
    }

    //Inventory
    public static List<Player> refuseInvEditAccess = new ArrayList<>();

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
        Chat.opBroadcast(message);
    }

    public static void PLUGIN_privateMessage(Player p, String message) {
        p.sendMessage(Reference.displayName + " " + ChatColor.GREEN + message);
    }

    //Functions
    public static void resetAll() {
        teleportTask.values().forEach(BukkitTask::cancel);
        teleportTask.clear();
        refuseInvEditAccess.clear();
        lastMessage.clear();
        lastMessage_repeatCount.clear();
    }

    public static int getPlayerCount() {
        return Bukkit.getOnlinePlayers().size();
    }

    //Player Specific Functions
    public static void resetPlayer(Player p) {
        interruptEvents(p);
        refuseInvEditAccess.remove(p);
        lastMessage.remove(p);
        lastMessage_repeatCount.remove(p);
    }

    //Interrupt Functions
    public static void interruptEvents(Player p) {
        interruptEvents(p, false);
    }

    public static void interruptEvents(Player p, boolean fromAPI) {
        interruptTeleportTask(p);
        if (fromAPI)
            PLUGIN_privateMessage(p, "All Events/Tasks have been interrupted by another plugin.");
        else
            PLUGIN_privateMessage(p, "All Events/Tasks have been interrupted.");
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

    public static Interruptible[] getInterruptibles(Player p) {
        List<Interruptible> i = new ArrayList<>();
        if (isTeleporting(p))
            i.add(Interruptible.TELEPORT);

        return (Interruptible[]) i.toArray();
    }

    public static boolean isInterruptable(Player p) {
        if (teleportTask.containsKey(p))
            return true;

        return false;
    }
}
