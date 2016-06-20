package me.timothy.ats2.commands.lib;

import me.timothy.ats2.ATSPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by CyanThunderMC on 6/20/2016.
 */
public class ACPCommand implements CommandExecutor {
    public ATSPlugin plugin = ATSPlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}
