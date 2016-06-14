package me.timothy.ats2;

import me.timothy.ats2.autorestart.AutoRestart;
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

        //Post Initialization:
        AutoRestart.run();
    }

    @Override
    public void onDisable() {

    }
}
