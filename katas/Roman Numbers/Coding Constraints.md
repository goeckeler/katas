# Coding Constraints #

## One level of indentation per method. ##

If you need more than one level indentation from the start of the method body, create another method and call it. So one level of a loop or if statement is ok, but any deeper and you need to break it out into its own method.

## Don't use the ELSE keyword. ##

This one is tough. We're very used to using `if/else` or `switch/case`. But good object-oriented designs rely on polymorphism in place of conditional logic.

## One dot per line. ##

This is meant to enforce the Law of Demeter. So this would be a no-no: `invoice.lineltems.getLineltem(4)`. Although this discourages method-chaining in cases where a method returns the same object (a la JQuery), that isn't what this rule is trying to do. It's trying to stop you from reaching across class boundaries and digging into the guts of other objects.

## Don't abbreviate anything. ##

This is meant to enforce clarity, as well as identify duplication or misplaced responsibilities. If you're typing `mergeUserPreferencesFromDatabaseAndCookies()` too often, something is probably wrong, both in terms of what the method is doing and how many things are coupled to it.

## Keep entities small. ##

No method over 20 lines. No class over 50 lines. No package over 10 files.
