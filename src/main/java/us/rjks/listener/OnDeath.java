package us.rjks.listener;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import us.rjks.core.Main;
import us.rjks.utils.Drop;
import us.rjks.utils.PlayingPlayer;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 26.06.2021 / 11:54
 *
 **************************************************************************/

public class OnDeath implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        event.setDroppedExp(0);
        event.getDrops().clear();

        if (player.getKiller() != null) {
            Main.getGameManager().getConfig().playingPlayers.add(new PlayingPlayer(player, player.getKiller(), System.currentTimeMillis() + Main.getGameManager().getConfig().getSpawnProtection()));
        }

        int timeInSeconds = Math.round(player.getStatistic(Statistic.TIME_SINCE_DEATH) / 20);
        Drop drop = Main.getGameManager().getDropManager().getDropFromDuration(timeInSeconds);

        for (ItemStack item : drop.getItems()) {
            event.getDrops().add(item);
        }
    }

}
