package me.timothy.ats2.api;

import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.autorestart.AutoRestart;
import me.timothy.ats2.autorestart.lib.AutoRestartlib;
import me.timothy.ats2.config.ServerSettings;
import me.timothy.ats2.config.lib.ServerConfig;
import me.timothy.ats2.events.custom.TeleportEvent;
import me.timothy.ats2.playerinfo.GPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

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
    
    public static class homes {
        
        public static class S5_API {
            public static void enable(boolean enable) {
                ServerSettings.set(ServerConfig.ENABLE_HOME, enable);
            }

            public static boolean isEnabled() {
                return ServerSettings.getBoolean(ServerConfig.ENABLE_HOME);
            }
            
            public static class homeValues {
                public static int[] getStarter() {
                    return new int[] { ServerSettings.getInt(ServerConfig.STARTING_HOMES), ServerSettings.getInt(ServerConfig.STARTING_BUYABLEHOMES) };
                }
                
                public static void setStarter(int[] values) {
                    if (values.length != 2) {
                        throw new IllegalArgumentException("Values is not equal to 2.");
                    }
                    
                    int free = values[0];
                    int buyable = values[1];
                    
                    ServerSettings.set(ServerConfig.STARTING_HOMES, free);
                    ServerSettings.set(ServerConfig.STARTING_BUYABLEHOMES, buyable);
                }

                public static int[] getEnderDragon() {
                    return new int[] { ServerSettings.getInt(ServerConfig.ENDERDRAGON_HOMES), ServerSettings.getInt(ServerConfig.ENDERDRAGON_BUYABLEHOMES) };
                }

                public static void setEnderDragon(int[] values) {
                    if (values.length != 2) {
                        throw new IllegalArgumentException("Values is not equal to 2.");
                    }

                    int free = values[0];
                    int buyable = values[1];

                    ServerSettings.set(ServerConfig.ENDERDRAGON_HOMES, free);
                    ServerSettings.set(ServerConfig.ENDERDRAGON_BUYABLEHOMES, buyable);
                }

                public static int[] getWither() {
                    return new int[] { ServerSettings.getInt(ServerConfig.WITHER_HOMES), ServerSettings.getInt(ServerConfig.WITHER_BUYABLEHOMES) };
                }

                public static void setWither(int[] values) {
                    if (values.length != 2) {
                        throw new IllegalArgumentException("Values is not equal to 2.");
                    }

                    int free = values[0];
                    int buyable = values[1];

                    ServerSettings.set(ServerConfig.WITHER_HOMES, free);
                    ServerSettings.set(ServerConfig.WITHER_BUYABLEHOMES, buyable);
                }
            }
        }
    }
}
