# Exception Testing in Java

+ Language: Java
+ Level: Easy
+ Requires: Java 8, Maven, IDE recommended

## Spoiler Alert

A thorough round-up can be found in Rafal Borowiec's article blogged on goyello.com. A copy in Markdown is placed here named

> Different ways of testing exceptions in Java and JUnit

Have a look at his Github repo for more examples covering all sorts of tests: [https://github.com/kolorobot/unit-testing-demo](https://github.com/kolorobot/unit-testing-demo).

## Motivation

Good example for Mob Coding or Randoori. Makes you think more about what you are doing than just performing.

## Objective

Re-think how you test for exceptions. Make your tests more expressive. Learn new styles.

## Coding

Checkout the given kata so you are not troubled downloading all those nice and nifty libraries you may need.

There is a helper class called `Thrower` that you might want to use.

Now think about the following scenarios:

1. `throwsRuntime()`
2. `throwsChecked()`
2. `throwsRuntimeInsteadOfChecked()`
3. `throwsRuntimeWithCause()`

How are you going to handle those?
