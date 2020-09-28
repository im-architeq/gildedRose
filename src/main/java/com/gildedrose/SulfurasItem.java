package com.gildedrose;

public class SulfurasItem extends ItemType {

    public SulfurasItem(int sellIn, int quality) {
        super("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    @Override
    void updateItem() {
        sellIn = 0;
        quality = 80;
    }


}
