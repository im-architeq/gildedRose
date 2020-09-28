package com.gildedrose;

import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    @Test
    public void itemName() {
        String name = "foo";
        ItemType[] items = new ItemType[] { new StandardItem(name, 1, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].name).isEqualTo(name);
    }

    @Test
    public void standardItemQuality() {
        String name = "foo";
        ItemType[] items = new ItemType[] { new StandardItem(name, 1, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    public void standardItemQualityInAfterQualityExpiration() {
        String name = "foo";
        ItemType[] items = new ItemType[] { new StandardItem(name, 0, 2) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    public void standardItemSellIn() {
        String name = "foo";
        ItemType[] items = new ItemType[] { new StandardItem(name, 1, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
    }

    @Test
    public void standardItemQualityInAfterQualityExpirationIsNeverNegative() {
        String name = "foo";
        ItemType[] items = new ItemType[] { new StandardItem(name, 0, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    public void agedBrieQualityIncreasingWithPositiveSellIn() {
        String name = "Aged Brie";
        ItemType[] items = new ItemType[] { new AgedBrieItem(name, 1, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(1);
    }

    @Test
    public void agedBrieQualityIncreasingWithZeroSellIn() {
        String name = "Aged Brie";
        ItemType[] items = new ItemType[] { new AgedBrieItem(name, 0, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(2);
    }

    @Test
    public void agedBrieQualityIncreasingWithNegativeSellIn() {
        String name = "Aged Brie";
        ItemType[] items = new ItemType[] { new AgedBrieItem(name, -1, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(2);
    }

    @Test
    public void agedBrieQualityIncreasingUntilMaximumQualityValue() {
        int maxQuality = 50;

        String name = "Aged Brie";
        ItemType[] items = new ItemType[] { new AgedBrieItem(name, -1, 49) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(maxQuality);
    }

    @Test
    public void agedBrieQualityHasAlreadyMaxValue() {
        int maxQuality = 50;

        String name = "Aged Brie";
        ItemType[] items = new ItemType[] { new AgedBrieItem(name, -1, maxQuality) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(maxQuality);
    }

    @Test
    public void sulfurusHasPermanentQuality() {
        int sulfurasPermanentQuality = 80;

        String name = "Sulfuras, Hand of Ragnaros";
        ItemType[] items = new ItemType[]{new SulfurasItem(name, 0, sulfurasPermanentQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(sulfurasPermanentQuality);
    }

    @Test
    public void sulfurusHasNoSellIn() {
        int sulfurasPermanentQuality = 80;

        String name = "Sulfuras, Hand of Ragnaros";
        ItemType[] items = new ItemType[] { new SulfurasItem(name, 0, sulfurasPermanentQuality) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
    }

    @Test
    public void backstagePassesWithSellInGreater10Days() {
        String name = "Backstage passes to a TAFKAL80ETC concert";
        ItemType[] items = new ItemType[] { new BackstageItem(name, 11, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(11);
    }

    @Test
    public void backstagePassesWithSellInEquals10Days() {
        String name = "Backstage passes to a TAFKAL80ETC concert";
        ItemType[] items = new ItemType[] { new BackstageItem(name, 10, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(12);
    }

    @Test
    public void backstagePassesWithSellInSmaller5Days() {
        String name = "Backstage passes to a TAFKAL80ETC concert";
        ItemType[] items = new ItemType[] { new BackstageItem(name, 5, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(13);
    }

    @Test
    public void backstagePassesWithSellInZero() {
        String name = "Backstage passes to a TAFKAL80ETC concert";
        ItemType[] items = new ItemType[] { new BackstageItem(name, 0, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    public void backstagePassesWithSellInNegative() {
        String name = "Backstage passes to a TAFKAL80ETC concert";
        ItemType[] items = new ItemType[] { new BackstageItem(name, -1, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    public void conjuredItemQuality() {
        String name = "conjured";
        ItemType[] items = new ItemType[] { new ConjuredItem(name, 1, 2) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    public void conjuredItemQualityInAfterQualityExpiration() {
        String name = "conjured";
        ItemType[] items = new ItemType[] { new ConjuredItem(name, 0, 2) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    public void conjuredItemSellIn() {
        String name = "conjured";
        ItemType[] items = new ItemType[] { new ConjuredItem(name, 1, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
    }

    @Test
    public void conjuredQualityInAfterQualityExpirationIsNeverNegative() {
        String name = "conjured";
        ItemType[] items = new ItemType[] { new ConjuredItem(name, 0, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(0);
    }


}
