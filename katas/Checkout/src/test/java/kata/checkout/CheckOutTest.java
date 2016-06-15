package kata.checkout;

// Classical TDD Loop
//
// INSTRUCTIONS
//
// 1) Uncomment out a test and save/compile
// 2) run tests; see red
// 3) change the code; see green
// 4) Refactor.
// 5) run tests; see green
// 6) goto step 1

/*
(Background information for the tests.)
Scan prices of As, Bs, Cs, and Ds.

   Item   Unit    Special
          Price   Price
--------------------------
    A     50      3 for 130
    B     30      2 for 45
    C     20
    D     15
*/
public class CheckOutTest
{
//  // Section 1 begins here
//
//  @Test public void priceIsZeroWhenNoGoods()
//      throws Exception
//  {
//    assertEquals(0, price(""));
//  }
//
//  @Test public void priceIsFiftyWhenGoodIsA()
//      throws Exception
//  {
//    assertEquals(50, price("A"));
//  }
//
//  @Test public void priceIsEightyWhenGoodsAreAB()
//      throws Exception
//  {
//    assertEquals(80, price("AB"));
//  }
//
//  @Test public void priceIsOneHundredFifteenWhenGoodsAreCDBA()
//      throws Exception
//  {
//    assertEquals(115, price("CDBA"));
//  }
//
//  // Section 1 ends here
//
//  // Section 2 begins here
//
//  @Test public void priceIsOneHundredWhenGoodsAreAA()
//      throws Exception
//  {
//    assertEquals(100, price("AA"));
//  }
//
//  @Test public void priceIsOneHundredThirtyWhenGoodsAreAAA()
//      throws Exception
//  {
//    assertEquals(130, price("AAA"));
//  }
//
//  @Test public void priceIsOneHundredEightyWhenGoodsAreAAAA()
//      throws Exception
//  {
//    assertEquals(180, price("AAAA"));
//  }
//
//  @Test public void priceIsTwoHundredThirtyWhenGoodsAreAAAAA()
//      throws Exception
//  {
//    assertEquals(230, price("AAAAA"));
//  }
//
//  @Test public void priceIsTwoHundredSixtyWhenGoodsAreAAAAAA()
//      throws Exception
//  {
//    assertEquals(260, price("AAAAAA"));
//  }
//
//  // Section 2 ends here
//
//  // Section 3 begins here
//
//  @Test public void priceIsOneHundredSixtyWhenGoodsAreAAAB()
//      throws Exception
//  {
//    assertEquals(160, price("AAAB"));
//  }
//
//  @Test public void priceIsOneHundredSeventyFiveWhenGoodsAreAAABB()
//      throws Exception
//  {
//    assertEquals(175, price("AAABB"));
//  }
//
//  @Test public void priceIsOneHundredNinetyWhenGoodsAreAAABBD()
//      throws Exception
//  {
//    assertEquals(190, price("AAABBD"));
//  }
//
//  @Test public void priceIsOneHundredNinetyWhenGoodsAreDABABA()
//      throws Exception
//  {
//    assertEquals(190, price("DABABA"));
//  }
//
//  // Section 3 ends here
//
//  // Section 4 starts here
//
//  @Test public void totalIncrementsAccordingToPricePlanPerScan()
//      throws Exception
//  {
//
//    CheckOut checkout = new CheckOut();
//
//    assertEquals(0, checkout.total());
//
//    checkout.scan('A');
//    assertEquals(50, checkout.total());
//
//    checkout.scan('B');
//    assertEquals(80, checkout.total());
//
//    checkout.scan('A');
//    assertEquals(130, checkout.total());
//
//    checkout.scan('A');
//    assertEquals(160, checkout.total());
//
//    checkout.scan('B');
//    assertEquals(175, checkout.total());
//  }

  // Section 4 ends here

  private int price(CharSequence goods) {
    CheckOut checkOut = new CheckOut();

    for (int i = 0; i < goods.length(); ++i) {
      char good = goods.charAt(i);
      checkOut.scan(good);
    }

    return checkOut.total();
  }
}
