# Checkout Kata #

+ Language: Java
+ Level: Medium
+ Requires: Java 8+, IDE recommended

## Credits ##

This kata is originally presented by Dave Thomas on his blog, see "[Kata Nine: Back to the CheckOut](http://codekata.com/kata/kata09-back-to-the-checkout/)" on [http://codekata.com/kata/kata09-back-to-the-checkout/](http://codekata.com/kata/kata09-back-to-the-checkout/). Yes, we are talking about Dave Thomas "The Pragmatic Programmer".

Most of the code has been originally published by Mike Finney on [https://github.com/finneycanhelp/kata-java-refactor-checkout](https://github.com/finneycanhelp/kata-java-refactor-checkout).

The code has been polished a bit to be more concise and run with current platforms.

## Kata ##

We are about to implement the code for a supermarket checkout that calculates the total price of a number of items. In a normal supermarket, things are identified using *Stock Keeping Units*, or *SKUs*. We will simply use letters to identify any given product, that is A, B, C, and so on. 

Our goods are priced individually. In addition, prices change if you buy more than a single item. Especially our supermarket likes "buy n of them, and they’ll cost you y cents". For example, item ‘A’ might cost 50 cents individually, but this week we have a special offer: buy three ‘A’s and they’ll cost you 1.30€. 

In fact this week’s prices are:

| Item | Unit Price | Special Price |
| -----| ---------- | ------------- |
|   A  |        50  | 3 for 130     |
|   B  |        30  | 2 for 45      |
|   C  |        20  | -             |
|   D  |        15  | -             |

Our checkout accepts items in any order, so that if we scan a B, an A, and another B, we’ll recognize the two B’s and price them at 45 (for a total price so far of 95). Because the pricing changes frequently, we need to be able to pass in a set of pricing rules each time we start handling a checkout transaction.

To get you started here a valid test cases in pseudo code:

```
begin check_totals
    assert_equal(  0, price(""))
    assert_equal( 50, price("A"))
    assert_equal( 80, price("AB"))
    assert_equal(115, price("CDBA"))

    assert_equal(100, price("AA"))
    assert_equal(130, price("AAA"))
    assert_equal(180, price("AAAA"))
    assert_equal(230, price("AAAAA"))
    assert_equal(260, price("AAAAAA"))

    assert_equal(160, price("AAAB"))
    assert_equal(175, price("AAABB"))
    assert_equal(190, price("AAABBD"))
    assert_equal(190, price("DABABA"))
end

begin check_after_each_scan
    checkout = new CheckOut(RULES);
    assert_equal(  0, checkout.total)
    checkout.scan("A");  assert_equal( 50, checkout.total)
    checkout.scan("B");  assert_equal( 80, checkout.total)
    checkout.scan("A");  assert_equal(130, checkout.total)
    checkout.scan("A");  assert_equal(160, checkout.total)
    checkout.scan("B");  assert_equal(175, checkout.total)
end
```