package com.gildedrose;

public class Item
{
  public String name;

  public int sellIn;

  public int quality;

  public Item(final String name, final int sellIn, final int quality) {
    this.name = name;
    this.sellIn = sellIn;
    this.quality = quality;
  }

  @Override
  public String toString() {
    return this.name + ", " + this.sellIn + ", " + this.quality;
  }
}
