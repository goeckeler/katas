# Calculate squares w/o floating point arithmetics

+ Language: Any
+ Level: Easy
+ Requires: Test framework

## Motivation

Sometimes performance matters. Sometimes you simply should know what all these operators are about. And sometimes you might think about how a simple algorithm can be scaled and executed in parallel. 

## Objective

Start coding in a team. This little brain teaser can be a good change from daily routine, but it is definitly solved faster in a team effort.

## Coding

Write a programm that calculates squares of any given integer. However, you are not allowed to use multiplication, division, modulo or a library routine. And you are not allowed to use any floating point arithmetics.

Here is a formula that might help you to evolve the algorithm:

    n^2 = 100 * (n-25) + (n-50)^2
