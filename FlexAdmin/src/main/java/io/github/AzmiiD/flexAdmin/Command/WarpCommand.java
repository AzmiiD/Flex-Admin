package io.github.AzmiiD.flexAdmin.Command;

import io.github.AzmiiD.flexAdmin.FlexAdmin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private final FlexAdmin plugin;

    public WarpCommand(FlexAdmin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Command Only Use Player");
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("Use: /warp <nama>");
            return true;
        }

        String warpName = args[0];

        if (!plugin.getWarpsConfig().contains(warpName)) {
            player.sendMessage("Warp Not Found");
            return true;
        }

        World world = Bukkit.getWorld(plugin.getWarpsConfig().getString(warpName + ".world"));
        double x = plugin.getWarpsConfig().getDouble(warpName + ".x");
        double y = plugin.getWarpsConfig().getDouble(warpName + ".y");
        double z = plugin.getWarpsConfig().getDouble(warpName + ".z");
        float yaw = (float) plugin.getWarpsConfig().getDouble(warpName + ".yaw");
        float pitch = (float) plugin.getWarpsConfig().getDouble(warpName + ".pitch");

        Location loc = new Location(world, x, y, z, yaw, pitch);
        player.teleport(loc);
        player.sendMessage("Teleport to warp" + warpName + ".");

        return true;
    }
}
