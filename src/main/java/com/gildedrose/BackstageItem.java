package com.gildedrose;

public class BackstageItem extends ItemType {

    public BackstageItem( int sellIn, int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    @Override
    void updateItem() {
        if (sellIn <= 0) {
            quality = 0;
            return;
        }
        if (quality >= 50) {
            quality = 50;
            return;
        }
        if (sellIn >= 11) {
            quality = quality + 1;
        }
        if (sellIn < 11 && sellIn > 5) {
            quality = quality + 2;
        }
        if (sellIn <= 5) {
            quality = quality + 3;
        }
        sellIn = sellIn - 1;
    }


}
