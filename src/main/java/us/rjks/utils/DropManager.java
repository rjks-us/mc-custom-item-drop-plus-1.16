package us.rjks.utils;

import org.bukkit.plugin.Plugin;
import us.rjks.module.ModuleType;
import us.rjks.module.SpigotModule;

import java.util.ArrayList;
import java.util.logging.Level;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 26.06.2021 / 10:38
 *
 **************************************************************************/

public class DropManager extends SpigotModule {

    private ArrayList<Drop> drops = new ArrayList<>();

    public DropManager(Plugin plugin, String directory, String name, ModuleType type, boolean autoCreate) {
        super(plugin, directory, name, type, autoCreate);
    }

    public void loadDrops() {
        int amount = 0;
        for (String key : getConfig().getConfigurationSection("").getKeys(false)) {
            Drop drop = new Drop(Integer.parseInt(key));
            String path = key + ".";
            for (String pools : getConfig().getConfigurationSection(key).getKeys(false)) {
                DropPool dropPool = new DropPool(pools, getConfig().getInt(path + pools + ".drops"));
                int item = 0;
                while (getConfig().get(path + pools + "." + item) != null) {
                    dropPool.addItemStack(getConfig().getItemStack(path + pools + "." + item));
                    item++;
                }
                drop.addPool(dropPool);
            }
            amount++;
            drops.add(drop);
        }
        getPlugin().getLogger().log(Level.INFO, "[DROP] Loaded " + amount + " drop configurations");
    }

    public Drop getDropFromDuration(int duration) {
        Drop highest = null;
        for (Drop drop : drops) {
            if (drop.getDropDuration() < duration) highest = drop;
        }
        return highest;
    }
}
