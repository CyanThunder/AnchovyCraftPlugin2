package me.timothy.ats2.playerinfo.lib;

import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.config.ServerSettings;
import me.timothy.ats2.config.lib.ServerConfig;
import me.timothy.ats2.playerinfo.PlayerData;
import org.bukkit.Achievement;
import org.bukkit.entity.Player;

/**
 * Created by CyanThunderMC on 6/9/2016.
 */
public class S5_PlayerHome {
    private static ATSPlugin plugin = ATSPlugin.getInstance();
    private static int starting_homes = i(ServerConfig.STARTING_HOMES);
    private static int starting_buyablehomes = i(ServerConfig.STARTING_BUYABLEHOMES);
    private static int enderdragon_homes = i(ServerConfig.ENDERDRAGON_HOMES);
    private static int enderdragon_buyable_homes = i(ServerConfig.ENDERDRAGON_BUYABLEHOMES);
    private static int wither_homes = i(ServerConfig.WITHER_HOMES);
    private static int wither_buyable_homes = i(ServerConfig.WITHER_BUYABLEHOMES);
    
    private static int i(ServerConfig config) {
        return ServerSettings.getInt(config);
    }
    
    public static int getUsableHomesCount(Player p) {
        int homes = getFreeHomeCount(p);
        homes += getBroughtHomesCount(p);

        return homes;
    }
    
    public static int getBroughtHomesCount(Player p) {
        //Not Implemented

        //Get from config file.

        return 0;
    }

    public static int getBuyableHomeCount(Player p) {
        PlayerData pd = new PlayerData(p);
        int homes = starting_buyablehomes;
        //Enderdragon defeated:
        if (pd.hasAchievement(Achievement.THE_END)) {
            homes += enderdragon_buyable_homes;
        }

        //Wither defeated
        if (pd.hasAchievement(Achievement.KILL_WITHER)) {
            homes += wither_buyable_homes;
        }
        return homes;
    }

    public static int getFreeHomeCount(Player p) {
        PlayerData pd = new PlayerData(p);
        int homes = starting_homes;
        //Enderdragon defeated:
        if (pd.hasAchievement(Achievement.THE_END)) {
            homes += enderdragon_homes;
        }

        //Wither defeated
        if (pd.hasAchievement(Achievement.KILL_WITHER)) {
            homes += wither_homes;
        }
        return homes;
    }
}
