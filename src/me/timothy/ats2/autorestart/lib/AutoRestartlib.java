package me.timothy.ats2.autorestart.lib;

import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CyanThunderMC on 6/11/2016.
 */
public class AutoRestartlib {
    //Store Data
    //DEFAULT :
    public static int[] autoRestartDelay = { 9, 0, 0 };
    public static int[] warningHours = { 8, 7, 6, 5, 4, 3, 2, 1 };
    public static int[] warningMinutes = { 30, 15, 10, 5, 3, 2, 1 };

    private static List<BukkitTask> tasks = new ArrayList<>();

    //Function access stuff
    public static int[] getAutoRestartDelay() {
        return autoRestartDelay;
    }

    public static long getARDSecs() {
        int[] waitTime = getAutoRestartDelay();
        return (((waitTime[0] * 60) + waitTime[1]) * 60) + waitTime[2];
    }

    public static int[] getWarningHours() {
        return warningHours;
    }

    public static int[] getWarningMinutes() {
        return warningMinutes;
    }

    public static Long[] getWarningHoursInSeconds() {
        List<Long> longArray = new ArrayList<>();

        for (int i : getWarningHours()) {
            longArray.add(SimpleTimeConversion.HoursToSeconds(i));
        }

        return (Long[]) longArray.toArray();
    }

    public static Long[] getWarningMinutesInSeconds() {
        List<Long> longArray = new ArrayList<>();

        for (int i : getWarningMinutes()) {
            longArray.add(SimpleTimeConversion.MinutesToSeconds(i));
        }

        return (Long[]) longArray.toArray();
    }

    public static void setAutoRestartDelay(int[] autoRestartDelay) {
        AutoRestartlib.autoRestartDelay = autoRestartDelay;
    }

    public static void setWarningHours(int[] warningHours) {
        AutoRestartlib.warningHours = warningHours;
    }

    public static void setWarningMinutes(int[] warningMinutes) {
        AutoRestartlib.warningMinutes = warningMinutes;
    }

    public static List<BukkitTask> getTasks() {
        return tasks;
    }

    public static void stopTasks() {
        for (BukkitTask task : tasks) {
            task.cancel();
        }
        tasks.clear();
    }

    public static void addTask(BukkitTask task) {
        tasks.add(task);
    }

    //Quick conversion stuff
    public static class SimpleTimeConversion {
        public static long HoursToMinutes(int hours){
            return hours * 60;
        }
        public static long HoursToSeconds(int hours) {
            return MinutesToSeconds(HoursToMinutes(hours));
        }
        public static long MinutesToSeconds(long minutes){
            return minutes * 60;
        }
    }
}
