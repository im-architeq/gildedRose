package com.gildedrose;

public class ConjuredItem extends ItemType {

    public ConjuredItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    void updateItem() {
        if(quality <= 1){
            quality = 0;
        }
        if (quality > 1) {
            quality = quality - 2;
        }
        if (sellIn <= 0 && quality > 1) {
            quality = quality - 2;
        }
        sellIn = sellIn - 1;

    }


}
