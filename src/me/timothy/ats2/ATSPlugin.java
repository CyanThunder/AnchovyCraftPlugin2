package me.timothy.ats2;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Tim on 5/26/2016.
 */
public class ATSPlugin extends JavaPlugin {
    public static ATSPlugin instance;

    public static ATSPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }
}
