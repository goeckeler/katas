# Object Calisthenics Rules #

## One level of indentation per method. ##

If you need more than one level indentation from the start of the method body, create another method and call it. So one level of a loop or if statement is ok, but any deeper and you need to break it out into its own method.

## Don't use the ELSE keyword. ##

This one is tough. We're very used to using `if/else` or `switch/case`. But good object-oriented designs rely on polymorphism in place of conditional logic.

## Wrap all primitives and Strings. ##

That means instead of `var zipCode : String`, you need `var zipCode : ZipCode`, and instead of `var age : int`, you need `var age : Age`. The idea is to ensure that everything is an object, that the purpose of everything is self-evident from its type, and that behavior related to that object has somewhere to go.

## Use first class collections. ##

This means you can't have `var cartltems : ArrayList`, but instead have `var cartltems : Cartltems`. This means that behavior related to the collection has a place to live, and that the collection should contain no other instance variables.

## One dot per line. ##

This is meant to enforce the Law of Demeter. So this would be a no-no: `invoice.lineltems.getLineltem(4)`. Although this discourages method-chaining in cases where a method returns the same object (a la JQuery), that isn't what this rule is trying to do. It's trying to stop you from reaching across class boundaries and digging into the guts of other objects.

## Don't abbreviate anything. ##

This is meant to enforce clarity, as well as identify duplication or misplaced responsibilities. If you're typing `mergeUserPreferencesFromDatabaseAndCookies()` too often, something is probably wrong, both in terms of what the method is doing and how many things are coupled to it.

## Keep entities small. ##

No class over 50 lines, and no package over 10 files.

## No classes with more than two instance variables. ##

This is meant to ruthlessly enforce the single responsibility principle for objects. If you need more instance variables, break them into composed objects.

## No getter, setter, and property calls to other objects. ##

This mandates the principle to "Tell, don't ask" and enforces strong encapsulation boundaries.
