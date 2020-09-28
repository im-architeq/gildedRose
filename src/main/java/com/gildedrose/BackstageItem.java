package com.gildedrose;

public class BackstageItem extends ItemType {

    public BackstageItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    void updateItem() {
        if (quality < 50) {
            quality = quality + 1;
            if (sellIn < 11) {
                if (quality < 50) {
                    quality = quality + 1;
                }
            }
            if (sellIn < 6) {
                if (quality < 50) {
                    quality = quality + 1;
                }
            }
            if (sellIn <= 0) {
                quality = 0;
            }
        }
        sellIn = sellIn - 1;
    }


}
