package com.gildedrose;

public abstract class ItemType extends Item{

    public ItemType(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    abstract void updateItem();
}
