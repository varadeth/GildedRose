package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void shouldLowerQualityForEachPassedDay() {
        Item[] items = new Item[] {new Item("foo", 10, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void shouldLowerQualityTwiceIfDayBelowSellIn() {
        Item[] items = new Item[] {new Item("foo", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    void shouldNotLowerQualityIfAlreadyBelow0() {
        Item[] items = new Item[]{new Item("foo", 10, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void shouldIncreaseTheQualityIfNameIsAgedBrie() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void shouldNotBeGreaterThan50ForQualityCheck() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void shouldNotDecreaseBy2IfNameIsAgedBrie() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(14, app.items[0].quality);
    }

    @Test
    void shouldIncreaseQualityAppropriatelyForBackstagePasses() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 8, 10)};
        GildedRose app = new GildedRose(items);
        for(int i = 0; i < 7; i++) {
            app.updateQuality();
        }
        assertEquals(28, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(31, app.items[0].quality);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void shouldNotSellSulfurasItem() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 8, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].sellIn);
        assertEquals(10, app.items[0].quality);
    }

    @Test
    void shouldLowerQualityIfSulfurasItemSellInGoesBelow0() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(9, app.items[0].quality);
    }

    @Test
    void shouldQualityNeverGoAbove50ForBackstagePasses() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 8, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(7, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }
}
