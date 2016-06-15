package kata;

// unique entry point to get access to all assertThat methods and utility methods (e.g. entry)
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

public class FeatureTestForAssertJ
{
  private Creature frodo = new Creature("Frodo", Race.HOBBIT);
  private Creature pippin = new Creature("Pippin", Race.HOBBIT);
  private Creature merry = new Creature("Merry", Race.HOBBIT);
  private Creature sauron = new Creature("Sauron", Race.MAIA);
  private Creature sam = new Creature("Sam", Race.HOBBIT);
  private Creature boromir = new Creature("Boromir", Race.MAN);
  private Creature gandalf = new Creature("Gandalf", Race.MAIA);
  private Creature aragorn = new Creature("Aragorn", Race.MAN);
  private Creature legolas = new Creature("Legolas", Race.ELF);
  private Creature gimli = new Creature("Gimli", Race.DWARF);

  private List<Creature> fellowshipOfTheRing = new ArrayList<>();

  @Before
  public void startUp() {
    fellowshipOfTheRing.add(frodo);
    fellowshipOfTheRing.add(sam);
    fellowshipOfTheRing.add(merry);
    fellowshipOfTheRing.add(pippin);
    fellowshipOfTheRing.add(gandalf);
    fellowshipOfTheRing.add(legolas);
    fellowshipOfTheRing.add(gimli);
    fellowshipOfTheRing.add(aragorn);
    fellowshipOfTheRing.add(boromir);
  }

  @Test
  public void demonstrateFeatures() {
    // in the following examples, fellowshipOfTheRing is a List of TolkienCharacter

    // basic assertions
    assertThat(frodo.getName()).isEqualTo("Frodo");
    assertThat(frodo).isNotEqualTo(sauron).isIn(fellowshipOfTheRing);

    // String specific assertions
    assertThat(frodo.getName()).startsWith("Fro").endsWith("do").isEqualToIgnoringCase("frodo");

    // collection specific assertions
    assertThat(fellowshipOfTheRing).hasSize(9).contains(frodo, sam).doesNotContain(sauron);

    // using extracting magical feature to check fellowshipOfTheRing characters name :)
    assertThat(fellowshipOfTheRing).extracting("name").contains("Boromir", "Gandalf", "Frodo",
        "Legolas").doesNotContain("Sauron", "Elrond");

    // Extracting with Java 8 love
    assertThat(fellowshipOfTheRing).extracting(character -> character.getRace().toString()).contains("HOBBIT",
        "ELF").doesNotContain("ORC");

    // filter collection before assertion
    assertThat(fellowshipOfTheRing).filteredOn("race", Race.HOBBIT).containsOnly(sam, frodo, pippin, merry);

    // filter collection with java 8 lambda predicate
    assertThat(fellowshipOfTheRing).filteredOn(character -> character.getName().contains("o")).containsOnly(aragorn,
        frodo, legolas, boromir);

    // combining filtering and extraction (yes we can)
    assertThat(fellowshipOfTheRing).filteredOn(character -> character.getName().contains("o")).containsOnly(aragorn,
        frodo, legolas, boromir).extracting(character -> character.getRace().toString()).contains("HOBBIT", "ELF",
            "MAN");

    // and many more assertions : map, dates (java 7 and java 8), file, numbers, exceptions ...
  }
}

class Creature
{
  final String name;
  final Race race;

  public Creature(String name, Race race) {
    this.name = name;
    this.race = race;
  }

  public String getName() {
    return toString();
  }

  public Race getRace() {
    return race;
  }

  public String toString() {
    return StringUtils.defaultString(name);
  }
}

enum Race
{
 HOBBIT,
 ELF,
 MAN,
 ORC,
 MAIA,
 DWARF
};
