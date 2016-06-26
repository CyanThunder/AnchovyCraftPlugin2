package me.timothy.ats2.playerinfo;

import com.sun.javaws.exceptions.InvalidArgumentException;
import me.timothy.ats2.ATSPlugin;
import me.timothy.ats2.lib.Reference.Type;
import me.timothy.ats2.playerinfo.lib.PlayerConfig;
import org.bukkit.Achievement;
import org.bukkit.entity.Player;

import java.util.EmptyStackException;

/**
 * Created by CyanThunderMC on 5/31/2016.
 */
public class PlayerData {
    private Player player;
    private ATSPlugin plugin = ATSPlugin.getInstance();

    public PlayerData(Player p) {
        player = p;
    }

    public Player getPlayer() {
        return player;
    }

    //FAKE Handlers:
    private double fake_KDR() {
        int kills = getInt(PlayerConfig.KILLS);
        int deaths = getInt(PlayerConfig.DEATHS);
        double ratio;

        if (deaths != 0) {
            ratio = (float) kills / (float) deaths;
        } else {
            ratio = kills + 1;
        }

        String kdr = String.format("%.2f", ratio);

        return Double.parseDouble(kdr);
    }

    //Config Set/Get
    public void set(PlayerConfig config, Object value) {
        plugin.getConfig().set(player.getUniqueId() + "." + config.getPath(), value);
        plugin.saveConfig();
    }

    public Object getConfig(PlayerConfig config) {
        //Handle "FAKEs":
        if (config.isFake()) {
            switch (config.name()) {
                case "KDR":
                    //TYPE: DOUBLE
                    return fake_KDR();
            }
        }

        return plugin.getConfig().get(player.getUniqueId() + "." + config.getPath(), config.getDefualt());
    }

    public String getString(PlayerConfig config) {
        if (!configTypeMatch(Type.STRING, config)) {
            throw new IllegalArgumentException("Invalid Object State.");
        } else {
            return (String) getConfig(config);
        }
    }

    public int getInt(PlayerConfig config) {
        return getInteger(config);
    }

    public int getInteger(PlayerConfig config) {
        if (!configTypeMatch(Type.INTEGER, config)) {
            throw new IllegalArgumentException("Invalid Object State.");
        } else {
            return (Integer) getConfig(config);
        }
    }

    public boolean getBoolean(PlayerConfig config) {
        if (!configTypeMatch(Type.BOOLEAN, config)) {
            throw new IllegalArgumentException("Invalid Object State.");
        } else {
            return (Boolean) getConfig(config);
        }
    }

    public double getDouble(PlayerConfig config) {
        if (!configTypeMatch(Type.DOUBLE, config)) {
            throw new IllegalArgumentException("Invalid Object State.");
        } else {
            return (Double) getConfig(config);
        }
    }

    public float getFloat(PlayerConfig config) {
        if (!configTypeMatch(Type.FLOAT, config)) {
            throw new IllegalArgumentException("Invalid Object State.");
        } else {
            return (Float) getConfig(config);
        }
    }

    public boolean configTypeMatch(Type main, PlayerConfig config) {
        return configTypeMatch(main, config.getType());
    }

    public boolean configTypeMatch(Type main, Type compare) {
        return (main == compare);
    }

    public boolean hasAchievement(Achievement achievement) {
        return player.hasAchievement(achievement);
    }

    public void clearMem() {
        GPlayer.resetPlayer(player);
    }
}
