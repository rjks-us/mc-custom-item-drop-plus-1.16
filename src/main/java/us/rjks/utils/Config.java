package us.rjks.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import us.rjks.module.ModuleType;
import us.rjks.module.SpigotModule;

import java.util.ArrayList;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 26.06.2021 / 12:14
 *
 **************************************************************************/

public class Config extends SpigotModule {

    public ArrayList<PlayingPlayer> playingPlayers = new ArrayList<>();

    public Config(Plugin plugin, String directory, String name, ModuleType type, boolean autoCreate) {
        super(plugin, directory, name, type, autoCreate);
    }

    public int getSpawnProtection() {
        return getConfig().getInt("spawnprotection") * 20;
    }

    public boolean playerCanDamagePlayer(Player player, Player target) {
        try {
            for (PlayingPlayer playingPlayer : playingPlayers) {
                if (playingPlayer.getPlayer().equals(playingPlayer) && playingPlayer.getTarget().equals(target)) {
                    if (playingPlayer.canDamage()) {
                        playingPlayers.remove(playingPlayer);
                    } else {
                        return false;
                    }
                }
                if (playingPlayer.getPlayer().equals(target) && playingPlayer.getTarget().equals(player)) {
                    if (playingPlayer.canDamage()) {
                        playingPlayers.remove(playingPlayer);
                    } else {
                        return false;
                    }
                }
            }
        } catch (Exception ex) {
        }
        return true;
    }
}
