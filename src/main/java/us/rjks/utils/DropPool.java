package us.rjks.utils;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 26.06.2021 / 11:23
 *
 **************************************************************************/

public class DropPool {

    private String name;
    private int dropRate;

    private ArrayList<ItemStack> items = new ArrayList<>();

    public DropPool(String name, int dropRate) {
        this.name = name;
        this.dropRate = dropRate;
    }

    public void addItemStack(ItemStack stack) {
        items.add(stack);
    }

    public ArrayList<ItemStack> getItems() {
        int newDropRate = this.dropRate;
        if (items.size() < this.dropRate) newDropRate = items.size();

        ArrayList<ItemStack> itemStacks = new ArrayList<>();

        for (int i = 0; i < newDropRate; i++) {
            ItemStack stack = items.get(new Random().nextInt(newDropRate));
            itemStacks.add(stack);
        }
        return itemStacks;
    }

    public String getName() {
        return name;
    }

    public int getDropRate() {
        return dropRate;
    }
}
