package me.timothy.ats2.playerinfo.lib;

import me.timothy.ats2.playerinfo.PlayerData;
import org.bukkit.entity.Player;

/**
 * Created by CyanThunderMC on 6/26/2016.
 */
public class PlayerTag {
    public static String getTag(Player player, String tagFormat) {
        String tag = new PlayerData(player).getString(PlayerConfig.TAG);
        return tagFormat.replace("**ins(tag)", tag.replaceAll("COLOR&", "§"));
    }

    public static void setTag(Player player, String tag) {
        new PlayerData(player).set(PlayerConfig.TAG, tag);
    }

    public static void removeTag(Player player) {
        setTag(player, "");
    }
}
