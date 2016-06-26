package me.timothy.ats2.chatlib;

import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.chatlib.antispam.AntiSpam;
import me.timothy.ats2.chatlib.antispam.lib.SpamTrigger;
import me.timothy.ats2.config.ServerSettings;
import me.timothy.ats2.config.lib.ServerConfig;
import me.timothy.ats2.lib.Reference;
import me.timothy.ats2.playerinfo.GPlayer;
import me.timothy.ats2.playerinfo.PlayerData;
import me.timothy.ats2.playerinfo.lib.PlayerConfig;
import me.timothy.ats2.playerinfo.lib.PlayerTag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.permissions.ServerOperator;

/**
 * Created by CyanThunderMC on 6/1/2016.
 */
public class Chat {
    public static void opBroadcast(String msg) {
        Bukkit.getOnlinePlayers().stream().filter(ServerOperator::isOp).forEach(player -> player.sendMessage(msg));
        ConsoleChat.log(msg);
    }

    public static void PluginBroadcast(String msg) {
        Bukkit.broadcastMessage(Reference.displayName + " " + ChatColor.YELLOW + msg);
    }

    public static boolean useChatHandler() {
        return ServerSettings.getBoolean(ServerConfig.USE_CHATHANDLER);
    }

    public static void handleChat(AsyncPlayerChatEvent event) {
        //Create variables:
        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData(player);
        String message = event.getMessage();

        if (!event.isCancelled()) {
            boolean useAntiSpam = AntiSpam.isEnabled();
            //Start AntiSpam Vertifications:
            if (useAntiSpam) {
                SpamTrigger trigger = AntiSpam.playerChat(player, message);
                if (trigger.getTriggered()) {
                    event.setCancelled(true);
                }
            }

            //Start Format Modifications.
            if (useChatHandler()) {
                String tagFormat = ChatColor.DARK_GRAY + "[**ins(tag) " + ChatColor.DARK_GRAY + "]";
                String tag = PlayerTag.getTag(player, tagFormat);

                String nameWborder;

                ChatColor playerColor = GPlayer.getChatColor(player);
                Boolean hardmode = playerData.getBoolean(PlayerConfig.HARDMODE);

                String defualtbordercolor = ChatColor.WHITE + "";
                String hmbordercolor = ChatColor.RED + "";
                String[] playerBorder = new String[] { defualtbordercolor + "<", defualtbordercolor + ">"};
                if (hardmode)
                    playerBorder = new String[] { hmbordercolor + "{", hmbordercolor + "}"};

                nameWborder = playerBorder[0] + playerColor + player.getDisplayName() + playerBorder[2];

                String format = tag + " " +  nameWborder + " " + "%2$s";
                event.setFormat(format);
            }
        }
    }
}