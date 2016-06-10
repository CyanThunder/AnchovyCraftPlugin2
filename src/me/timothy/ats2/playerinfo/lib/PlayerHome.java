package me.timothy.ats2.playerinfo.lib;

import me.timothy.ats2.ATSPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Created by CyanThunderMC on 6/9/2016.
 */public class PlayerHome {
    static int maxHomes = 8;

    public static int getMaxHomes() {
        return maxHomes;
    }

    public static void setHome(Player p, String HOMENUMBER) {
        ATSPlugin plugin = ATSPlugin.getInstance();
        String uuid = p.getUniqueId().toString();

        plugin.getConfig().set(uuid + ".home" + HOMENUMBER + ".world", p.getWorld().getName());
        plugin.getConfig().set(uuid + ".home" + HOMENUMBER + ".x", p.getLocation().getX());
        plugin.getConfig().set(uuid + ".home" + HOMENUMBER + ".y", p.getLocation().getY());
        plugin.getConfig().set(uuid + ".home" + HOMENUMBER + ".z", p.getLocation().getZ());
        plugin.getConfig().set(uuid + ".home" + HOMENUMBER + ".yaw", p.getEyeLocation().getYaw());
        plugin.getConfig().set(uuid + ".home" + HOMENUMBER + ".pitch", p.getEyeLocation().getPitch());

        plugin.saveConfig();
    }

    public static Home getHome(Player p, String HOMENUMBER) {
        ATSPlugin plugin = ATSPlugin.getInstance();
        String uuid = p.getUniqueId().toString();

        World w = Bukkit.getServer().getWorld(plugin.getConfig().getString(uuid + ".home" + HOMENUMBER + ".world"));
        double x = plugin.getConfig().getDouble(uuid + ".home" + HOMENUMBER + ".x");
        double y = plugin.getConfig().getDouble(uuid + ".home" + HOMENUMBER + ".y");
        double z = plugin.getConfig().getDouble(uuid + ".home" + HOMENUMBER + ".z");
        double yaw = plugin.getConfig().getDouble(uuid + ".home" + HOMENUMBER + ".yaw");
        double pitch = plugin.getConfig().getDouble(uuid + ".home" + HOMENUMBER + ".pitch");

        return new Home(w, x, y, z, yaw, pitch);
    }

    public static class Home {
        World world;
        double x;
        double y;
        double z;
        double yaw;
        double pitch;

        public Home(World world, double x, double y, double z, double yaw, double pitch){
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
        }

        public World getWorld() {
            return world;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getZ() {
            return z;
        }

        public double getPitch() {
            return pitch;
        }

        public double getYaw() {
            return yaw;
        }
    }
}
