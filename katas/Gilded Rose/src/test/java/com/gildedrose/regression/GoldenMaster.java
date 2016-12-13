package com.gildedrose.regression;

import com.gildedrose.Item;

/**
 * A sample run of the Gilded Rose application. Note the usage of <code>System.out.prinln()</code> to produce output.
 */
public class GoldenMaster
  extends GoldenMasterTextRunner
{
  public static void main(final String[] args) {
    System.out.println("OMGHAI!");

    final Item[] items = new Item[] {
      new Item("+5 Dexterity Vest", 10, 20), new Item("Aged Brie", 2, 0), new Item("Elixir of the Mongoose", 5, 7),
      new Item("Sulfuras, Hand of Ragnaros", 0, 80), new Item("Sulfuras, Hand of Ragnaros", -1, 80),
      new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
      new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
      new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
      // this conjured item does not work properly yet
      new Item("Conjured Mana Cake", 3, 6)
    };

    // help me, cannot run the application ...
    // final GildedRose app = new GildedRose(items);

    final int days = 2;

    for (int i = 0; i < days; i++) {
      System.out.println("-------- day " + i + " --------");
      System.out.println("name, sellIn, quality");
      for (final Item item : items) {
        System.out.println(item);
      }
      System.out.println();

      // help me, cannot run the application ...
      // app.updateQuality();
    }
  }

  @Override
  protected void execute() {
    GoldenMaster.main(null);
  }
}
