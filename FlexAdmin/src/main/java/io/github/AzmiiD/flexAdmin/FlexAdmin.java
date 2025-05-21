package io.github.AzmiiD.flexAdmin;

import io.github.AzmiiD.flexAdmin.Command.DelWarpCommand;
import io.github.AzmiiD.flexAdmin.Command.SetWarpCommand;
import io.github.AzmiiD.flexAdmin.Command.WarpCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Set;

public final class FlexAdmin extends JavaPlugin {

    private File warpsFile;
    private FileConfiguration warpsConfig;

    @Override
    public void onEnable() {
        getLogger().info("Plugin Telah Aktif");
        createWarpsFile();

        getCommand("setwarp").setExecutor(new SetWarpCommand(this));
        getCommand("warp").setExecutor(new WarpCommand(this));
        getCommand("delwarp").setExecutor(new DelWarpCommand(this));
    }

    private void createWarpsFile() {
        warpsFile = new File(getDataFolder(), "warp.yml");
        if (!warpsFile.exists()) {
            warpsFile.getParentFile().mkdirs();
        }
        warpsConfig = YamlConfiguration.loadConfiguration(warpsFile);
    }

    public FileConfiguration getWarpsConfig() {
        return warpsConfig;
    }

    public void saveWarpsConfig() {
        try {
            warpsConfig.save(warpsFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
