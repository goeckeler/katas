# Mutation Testing

+ Language: Java
+ Level: Intermediate
+ Requires: Java 1.8+, Internet, Gradle, IDE strongly recommended

## Objective ##

100% code coverage, all set and done. Objections? Well, let's have a go at being smarter than the code and introduce bugs that are not detected.

In the epic battle between human mind and machine power it's your turn for victory. Only to automate stuff and force the machine to fight the battle alone.

## Code ##

Check out the code and build it with `gradlew build` on Mac or Linux or `gradlew.bat build` on Windows. Check the code coverage, should be 100%. Then start and try to change the code so that the tests don't break albeit the code is erraneous. Rinse and repeat. 

- How many defects can you introduce within 30 minutes?
- What kind of defects are these?
- Any more ideas?
- Any ideas how to automate the tedious bits?

## Reasoning ##

How do we know whether our tests are good enough?

One common measure is code coverage, but it has its limits. You can take any test suite providing good coverage, remove all of the assertions, and still have good coverage. However, the resulting test suite will be next to useless.

*The only real way to know whether our tests are doing their job is to see what happens when our code breaks.*

This is the idea behind mutation testing. We deliberately introduce defects into productive code and then see which tests break as a result. If a real defect is introduced and no test breaks, then we know that our tests are inadequate.

## References ##

Description                 | Source
----------------------------|--------------------------------
Summary, pretty good        | https://en.wikipedia.org/wiki/Mutation_testing
Automated test tool         | http://pitest.org/
Eclipse Integration         | https://github.com/philglover/pitclipse
IntelliJ integration        | http://plugins.jetbrains.com/plugin/?idea&pluginId=7119

## Credits ##

The sample code and the reasoning are shamelessly copied from https://github.com/hovinen/EasterEggHunt and all credits go to Bradford Hovinen for that. Thanks for adding this tool to my set of utilities. 
