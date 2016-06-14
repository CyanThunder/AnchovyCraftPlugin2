package me.timothy.ats2.config.lib;


import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.lib.Reference;
import me.timothy.ats2.lib.Reference.Type;

/**
 * Created by CyanThunderMC on 6/13/2016.
 */
public enum ServerConfig {
    ENABLE_HOME("settings.homes.enable", Type.BOOLEAN, true),
    STARTING_HOMES("settings.homes.starting.homes", Type.INTEGER, 3),
    STARTING_BUYABLEHOMES("settings.homes.starting.buyable_homes", Type.INTEGER, 2),
    ENDERDRAGON_HOMES("settings.homes.enderdragon.additional_homes", Type.INTEGER, 0),
    ENDERDRAGON_BUYABLEHOMES("settings.homes.enderdragon.additional_buyable_homes", Type.INTEGER, 2),
    WITHER_HOMES("settings.homes.wither.additional_homes", Type.INTEGER, 0),
    WITHER_BUYABLEHOMES("settings.homes.wither.additional_buyable_homes", Type.INTEGER, 3)
    ;

    private ATSPlugin plugin = ATSPlugin.getInstance();

    private String path;
    private Type type;
    private Object default_value;

    ServerConfig(String path, Type type, Object default_value) {
        this.path = path;
        this.type = type;
        this.default_value = default_value;
    }

    public String getPath() {
        return path;
    }

    public Type getType() {
        return type;
    }

    public Object getDefault() {
        return default_value;
    }

    public void setCurrentValue(Object value) {
        plugin.getConfig().set(getPath(), value);
        plugin.saveConfig();
    }

    public Object getCurrentValue() {
        return plugin.getConfig().get(getPath(), getDefault());
    }
}
