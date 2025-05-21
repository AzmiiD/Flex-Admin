package io.github.AzmiiD.flexAdmin.Command;

import io.github.AzmiiD.flexAdmin.FlexAdmin;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    private final FlexAdmin plugin;

    public SetWarpCommand(FlexAdmin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command Only Player");
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("Use: /setwarp <name>");
            return true;
        }

        String warpName = args[0];
        Location loc = player.getLocation();

        plugin.getWarpsConfig().set(warpName + ".world", loc.getWorld().getName());
        plugin.getWarpsConfig().set(warpName + ".x", loc.getX());
        plugin.getWarpsConfig().set(warpName + ".y", loc.getY());
        plugin.getWarpsConfig().set(warpName + ".z", loc.getZ());
        plugin.getWarpsConfig().set(warpName + ".yaw", loc.getYaw());
        plugin.getWarpsConfig().set(warpName + ".pitch", loc.getPitch());

        plugin.saveWarpsConfig();

        player.sendMessage("Warp " + warpName + "Telah di simpan");
        return true;
    }
}