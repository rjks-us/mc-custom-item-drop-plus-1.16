package us.rjks.core;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import us.rjks.listener.OnDamage;
import us.rjks.listener.OnDeath;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 26.06.2021 / 10:07
 *
 **************************************************************************/

public class Main extends JavaPlugin {

    private static GameManager gameManager;
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        try {
            gameManager = new GameManager(getPlugin());

            Bukkit.getPluginManager().registerEvents(new OnDeath(), this);
            Bukkit.getPluginManager().registerEvents(new OnDamage(), this);

            System.out.println("[GAME] Plugin successfully started");
            System.out.println("[GAME] Author: Salty#0299");
            System.out.println("[GAME] Version: 1.0.0");
            System.out.println("[GAME] Git: link.rjks.us/github");
            System.out.println("[GAME] Support: link.rjks.us/support");

        } catch (Exception e) {
            System.out.println("[INFO] Fatal error while loading the plugin");
            e.printStackTrace();
            System.out.println("[INFO] Plugin disabling due of the fatal error");
            gameManager.disablePlugin();
        }
    }

    @Override
    public void onDisable() {
        System.out.println("[GAME] Plugin successfully stopped");

        super.onDisable();
    }

    @Override
    public void onLoad() {
        System.out.println("[GAME] Plugin successfully loaded");

        super.onLoad();
    }

    public static Main getInstance() {
        return instance;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static Main getPlugin() {
        return instance;
    }
}
