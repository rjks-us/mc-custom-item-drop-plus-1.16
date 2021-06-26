package us.rjks.utils;

import org.bukkit.entity.Player;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 26.06.2021 / 12:12
 *
 **************************************************************************/

public class PlayingPlayer {

    private Player player, target;
    private long duration;

    public PlayingPlayer(Player player, Player target, long duration) {
        this.player = player;
        this.target = target;
        this.duration = duration;
        System.out.println(duration);
    }

    public boolean canDamage() {
        System.out.println(duration);
        System.out.println(System.currentTimeMillis());
        return (duration < System.currentTimeMillis());
    }

    public long getDuration() {
        return duration;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getTarget() {
        return target;
    }
}
