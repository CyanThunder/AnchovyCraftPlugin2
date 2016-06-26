package me.timothy.ats2.chatlib.antispam;

import me.timothy.ats2.chatlib.antispam.lib.ChatMessage;
import me.timothy.ats2.chatlib.antispam.lib.ChatTimeout;
import me.timothy.ats2.chatlib.antispam.lib.SpamReason;
import me.timothy.ats2.chatlib.antispam.lib.SpamTrigger;
import me.timothy.ats2.config.ServerSettings;
import me.timothy.ats2.config.lib.ServerConfig;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CyanThunderMC on 6/23/2016.
 */
public class AntiSpam {
    //Player Previous Messages:
    public static List<ChatMessage> chatCooldown = new ArrayList<>();
    public static List<ChatMessage> recentMessages = new ArrayList<>();
    public static List<ChatTimeout> timedOut = new ArrayList<>();

    public static SpamTrigger playerChat(Player player, String message) {
        //IGNORE CHECKS IF PLAYER IS OP & SPAM BYPASS IS TRUE.
        if (!(player.isOp() && ServerSettings.getBoolean(ServerConfig.OP_SPAM_BYPASS))) {
            if (getTotalRepeats(player) >= maxRepeatedMessages)
                return new SpamTrigger(player, SpamReason.REPEATED_MESSAGE, true);

            if (isTimedOut(player))
                return new SpamTrigger(player, SpamReason.CHAT_TIMEOUT, true);

            if (hasRecentMessage(player)) {
                return new SpamTrigger(player, SpamReason.TOO_FAST_MESSAGE, true);
            }
        }


        //If no antispam trigger - do stuff.
        playerChat_asfunct(player, message);
        return new SpamTrigger(player, SpamReason.FALSE, false);
    }

    //ChatTimeout Stuff
    public static boolean timeout(Player player, Integer tickLength) {
        if (isTimedOut(player))
            return false;

        ChatTimeout timeout = new ChatTimeout(player, tickLength);
        timedOut.add(timeout.timeout());
        return true;
    }

    public static boolean isTimedOut(Player p) {
        for (ChatTimeout timeout : timedOut) {
            if (timeout.getPlayer() == p) {
                return true;
            }
        }
        return false;
    }


    //--SAVE MESSAGES TO MEMORY + EXPIRE MESSAGES

    //HARDCODED
    //TODO: Soft code
    //[~2.5 minutes]
    private static int defualtMessageResetTime = 3000;
    //[~2 secs]
    private static int defualtMessageDelayTime = 40;

    public static int maxRepeatedMessages = 3;

    //Recent Messages Functions
    public static void playerChat_asfunct(Player player, String message) {
        ChatMessage rmChatMessage = new ChatMessage(player, message, defualtMessageResetTime, SpamReason.REPEATED_MESSAGE);
        recentMessages.add(rmChatMessage.startExpiration());

        ChatMessage dtChatMessage = new ChatMessage(player, message, defualtMessageDelayTime, SpamReason.TOO_FAST_MESSAGE);
        chatCooldown.add(dtChatMessage.startExpiration());
    }

    public static boolean hasRecentMessage(Player player) {
        for (ChatMessage message : chatCooldown) {
            if (message.getPlayer() == player) {
                return true;
            }
        }
        return false;
    }

    public static Integer getTotalRepeats(Player player) {
        int sameMessages = 0;
        for (ChatMessage message : recentMessages) {
            if (message.getPlayer() == player) {
                sameMessages++;
            }
        }

        return sameMessages;
    }

    //Functions
    public static boolean isEnabled() {
        return ServerSettings.getBoolean(ServerConfig.USE_ANTISPAM);
    }
}
