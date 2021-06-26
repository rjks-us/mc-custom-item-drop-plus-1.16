package us.rjks.utils;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 26.06.2021 / 10:38
 *
 **************************************************************************/

public class Drop {

    private int dropDuration;
    private ArrayList<DropPool> dropPools = new ArrayList<>();

    public Drop(int dropDuration) {
        this.dropDuration = dropDuration;
    }

    public void addPool(DropPool dropPool) {
        dropPools.add(dropPool);
    }

    public int getDropDuration() {
        return dropDuration;
    }

    public ArrayList<ItemStack> getItems() {
        if (dropPools.size() == 0) return null;
        return dropPools.get(new Random().nextInt(dropPools.size())).getItems();
    }
}
