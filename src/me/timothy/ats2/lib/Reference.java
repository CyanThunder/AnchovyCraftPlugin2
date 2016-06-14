package me.timothy.ats2.lib;

import net.md_5.bungee.api.ChatColor;

/**
 * Created by CyanThunderMC on 6/1/2016.
 */
public class Reference {
    public static final String name = "ATSPlugin";
    public static final String displayName = ChatColor.GRAY + "[" + ChatColor.RED + Reference.name + ChatColor.GRAY + "]";

    public enum Type {
        STRING,
        BOOLEAN,
        INTEGER,
        DOUBLE,
        FLOAT,
    }
}
