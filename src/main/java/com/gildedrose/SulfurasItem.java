package com.gildedrose;

public class SulfurasItem extends ItemType {

    public SulfurasItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    void updateItem() {
        sellIn = 0;
        quality = 80;
    }


}
