package com.gildedrose;

public class StandardItem extends ItemType {

    public StandardItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    void updateItem() {
        if (quality > 0) {
            quality = quality - 1;
        }
        if (sellIn <= 0 && quality > 0) {
            quality = quality - 1;
        }
        sellIn = sellIn - 1;
    }


}
