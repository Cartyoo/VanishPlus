package xyz.herberto.vanishplus;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.herberto.vanishplus.bstats.Metrics;
import xyz.herberto.vanishplus.commands.VanishCommand;
import xyz.herberto.vanishplus.papi.VanishPlusExpansion;

import java.io.IOException;
import java.util.Arrays;

public final class VanishPlus extends JavaPlugin {
    @Getter private static ConfigFile configFile;
    @Getter private static VanishPlus instance;

    @Override
    public void onEnable() {

        /* Register the Plugin Instance */
        instance = this;


        /* Register Commands */
        BukkitCommandManager manager = new BukkitCommandManager(this);
        Arrays.asList(
                new VanishCommand()
        ).forEach(manager::registerCommand);


        /* Load the config file */
        loadFiles();

        /* Register the PlaceholderAPI Expansion */
        if(this.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new VanishPlusExpansion(this).register();
        }

        /* Register bStats Metrics */
        Metrics metrics = new Metrics(this, 21273);


    }


    public void loadFiles() {
        try {
            configFile = new ConfigFile(this, "config.yml");

        } catch (IOException | InvalidConfigurationException e) {
            this.getLogger().info("=======================================================================================================");
            this.getLogger().info("There was an error while loading one or more of the files, please check for any configuration mistakes: ");
            e.printStackTrace();
            this.getLogger().info("=======================================================================================================");
        }
    }
}
