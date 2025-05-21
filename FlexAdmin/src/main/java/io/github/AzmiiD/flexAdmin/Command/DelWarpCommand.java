package io.github.AzmiiD.flexAdmin.Command;

import io.github.AzmiiD.flexAdmin.FlexAdmin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class DelWarpCommand implements CommandExecutor {

    private final FlexAdmin plugin;

    public DelWarpCommand(FlexAdmin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command Only Use Player");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("use: /delwarp <name>");
            return true;
        }

        String warpName = args[0];

        if (!plugin.getWarpsConfig().contains(warpName)) {
            sender.sendMessage("Warp " + warpName + " Not Found.");
            return true;
        }

        plugin.getWarpsConfig().set(warpName, null);
        plugin.saveWarpsConfig();

        sender.sendMessage("Warp " + warpName + " has to remove");
        return true;
    }
}
