package me.timothy.ats2.config;

import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.config.lib.ServerConfig;
import me.timothy.ats2.lib.Reference;

/**
 * Created by CyanThunderMC on 6/9/2016.
 */
public class ServerSettings {
    
    //Config Set/Get
    public static void set(ServerConfig config, Object value) {
        config.setCurrentValue(value);
    }

    public static Object getConfig(ServerConfig config) {
        return config.getCurrentValue();
    }

    public static String getString(ServerConfig config) {
        if (!configTypeMatch(Reference.Type.STRING, config)) {
            throw new IllegalArgumentException("Invalid Object State.");
        } else {
            return (String) getConfig(config);
        }
    }

    public static int getInt(ServerConfig config) {
        return getInteger(config);
    }

    public static int getInteger(ServerConfig config) {
        if (!configTypeMatch(Reference.Type.INTEGER, config)) {
            throw new IllegalArgumentException("Invalid Object State.");
        } else {
            return (Integer) getConfig(config);
        }
    }

    public static boolean getBoolean(ServerConfig config) {
        if (!configTypeMatch(Reference.Type.BOOLEAN, config)) {
            throw new IllegalArgumentException("Invalid Object State.");
        } else {
            return (Boolean) getConfig(config);
        }
    }

    public static double getDouble(ServerConfig config) {
        if (!configTypeMatch(Reference.Type.DOUBLE, config)) {
            throw new IllegalArgumentException("Invalid Object State.");
        } else {
            return (Double) getConfig(config);
        }
    }

    public static float getFloat(ServerConfig config) {
        if (!configTypeMatch(Reference.Type.FLOAT, config)) {
            throw new IllegalArgumentException("Invalid Object State.");
        } else {
            return (Float) getConfig(config);
        }
    }

    public static boolean configTypeMatch(Reference.Type main, ServerConfig config) {
        return configTypeMatch(main, config.getType());
    }

    public static boolean configTypeMatch(Reference.Type main, Reference.Type compare) {
        return (main == compare);
    }
}
