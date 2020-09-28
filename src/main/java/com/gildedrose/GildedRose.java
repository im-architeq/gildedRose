package com.gildedrose;

class GildedRose {
    ItemType[] items;

    public GildedRose(ItemType[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            items[i].updateItem();
        }
    }

}