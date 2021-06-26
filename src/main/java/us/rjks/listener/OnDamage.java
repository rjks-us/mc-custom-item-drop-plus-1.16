package us.rjks.listener;

import javafx.print.PageLayout;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import us.rjks.core.Main;
import us.rjks.utils.PlayingPlayer;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 26.06.2021 / 12:11
 *
 **************************************************************************/

public class OnDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            System.out.println(Main.getGameManager().getConfig().playerCanDamagePlayer(((Player) event.getDamager()).getPlayer(), ((Player) event.getEntity()).getPlayer()));
            if (!Main.getGameManager().getConfig().playerCanDamagePlayer(((Player) event.getDamager()).getPlayer(), ((Player) event.getEntity()).getPlayer())) {
                event.setCancelled(true);
                event.getDamager().sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getGameManager().getConfig().getConfig().getString("no-pvp")));
            } else {
                event.setCancelled(false);
            }
        }
    }


}
