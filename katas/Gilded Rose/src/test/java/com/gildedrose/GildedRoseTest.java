package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GildedRoseTest
{

  @Test
  public void foo() {
    final Item[] items = new Item[] {
      new Item("foo", 0, 0)
    };
    final GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals("fixme", app.items[0].name);
  }

}
