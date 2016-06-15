package demo;

import java.util.Set;
import java.util.TreeSet;

public class Shortcuts
{
  private static String[] forenames = {
    "Aaron", "Christian", "Filip", "Walter", "Stefan", "Niklas", "Johann", "Sebastian", "Daniel", "Jürgen", "Thorsten",
    "Timo", "Thomas", "Martin", "Rüdiger", "David", "Wolfgang", "Zacharias", "Simon", "Peter", "Meinhard", "Andreas",
    "Alexander", "Markus", "Marcel", "Gerald", "Fabian", "Achim", "Ben", "Nico", "Michael", "Jonas"
  };

  private static String[] surnames = {
    "Müller", "Mayer", "Maier", "Schmidt", "Huber", "Schuster", "Heinze", "Wittenberg", "Wahlberg", "Göckeler",
    "Heinemann", "Fath", "Schreinemakers", "Augstein", "Althaus", "Bach", "Schlosser", "Radebauer", "Bauer", "Ehrmann",
    "Weber", "Saalfeld", "Wißmach", "Langer", "Raub", "Ohnesorg", "Gierer", "Hombach"
  };

  private static Set<String> items = new TreeSet<>();

  static {
    for (String forename : forenames) {
      for (String surname : surnames) {
        items.add(forename + " " + surname);
      }
    }
  }

  public String findFirstSeq(final String name) {
    return items.stream().filter(item -> item.equalsIgnoreCase(name)).findFirst().orElse(null);
  }

  public String findFirstPar(final String name) {
    return items.parallelStream().filter(item -> item.equalsIgnoreCase(name)).findFirst().orElse(null);
  }

  public String findFirstFor(final String name) {
    for (String item : items) {
      if (item.equalsIgnoreCase(name)) { return item; }
    }
    return null;
  }
}
