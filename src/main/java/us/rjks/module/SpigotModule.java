package us.rjks.module;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 26.05.2021 / 18:06
 *
 **************************************************************************/

public class SpigotModule {

    private String name, directory;
    private ModuleType type;

    private File dir;

    private File file;
    private YamlConfiguration config;

    private Plugin plugin;

    private HashMap<String, Object> cache = new HashMap<>();

    public SpigotModule(Plugin plugin, String directory, String name, ModuleType type, boolean autoCreate) {
        this.name = name;
        this.directory = directory;
        this.type = type;
        this.dir = new File(directory);
        this.file = new File(getDirectory(), getName() + "." + getType().toString().toLowerCase());

        this.plugin = plugin;

        try {
            if (autoCreate) {
                if (!dirExists()) createDirectory();
                if (!fileExists()) createEmptyFile();

                loadFile();
            }
        } catch (Exception exception) {
        }
    }

    public void createEmptyFile() throws Exception {
        if (!fileExists()) {
            getFile().createNewFile();
        }
    }

    public void createDirectory() throws Exception {
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void loadFromCache(String name) throws Exception {
        if (!fileExists()) {
            Files.copy(plugin.getResource(name), new File(getDirectory(), name).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public void loadFile() {
        config = YamlConfiguration.loadConfiguration(getFile());
    }

    public void loadTemplate(String name) throws Exception {
        if (!dirExists()) createDirectory();
        loadFromCache(name);
    }

    public void reloadFile() throws Exception {
        cache.clear();
        config = YamlConfiguration.loadConfiguration(getFile());
    }

    public void save() throws Exception {
        config.save(getFile());
    }

    public boolean fileExists() {
        return file.exists();
    }

    public boolean dirExists() {
        return dir.exists();
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public String getName() {
        return name;
    }

    public String getDirectory() {
        return directory;
    }

    public ModuleType getType() {
        return type;
    }

    public HashMap<String, Object> getCache() {
        return cache;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public File getDir() {
        return dir;
    }
}
