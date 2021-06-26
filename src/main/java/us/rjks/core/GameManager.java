package us.rjks.core;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import us.rjks.module.ModuleType;
import us.rjks.utils.Config;
import us.rjks.utils.DropManager;

import java.util.logging.Level;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 26.06.2021 / 10:38
 *
 **************************************************************************/

public class GameManager {

    private DropManager dropManager;
    private Config config;

    private Plugin plugin;

    public GameManager(Plugin plugin) throws Exception {
        this.plugin = plugin;

        dropManager = new DropManager(plugin, plugin.getDataFolder() + "/", "drops", ModuleType.YML, false);
        dropManager.loadTemplate("drops.yml");
        dropManager.loadFile();
        dropManager.loadDrops();

        config = new Config(plugin, plugin.getDataFolder() + "/", "config", ModuleType.YML, false);
        config.loadTemplate("config.yml");
        config.loadFile();
    }

    public void reload() throws Exception {
        dropManager.reloadFile();
        config.reloadFile();
    }

    public void disablePlugin() {
        HandlerList.unregisterAll();
        Bukkit.getPluginManager().disablePlugin(getPlugin());
        getPlugin().getLogger().log(Level.INFO, "The Plugin has been disabled");
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Config getConfig() {
        return config;
    }

    public DropManager getDropManager() {
        return dropManager;
    }
}
