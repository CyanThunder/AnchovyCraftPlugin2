package me.timothy.ats2;

import me.timothy.ats2.autorestart.AutoRestart;
import me.timothy.ats2.events.eventhandlers.CustomEventHandler;
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

        //Register Event Handlers:
        getServer().getPluginManager().registerEvents(new CustomEventHandler(), this);

        //Post Initialization:
        AutoRestart.run();
    }

    @Override
    public void onDisable() {

    }
}
