package kata.lambdas.domain;

public abstract class Person implements Comparable<Person> {
    private String forename;
    private String surname;

    public Person(String forename, String surname) {
        assert forename != null;
        assert surname != null;

        this.forename = forename;
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public String getForename() {
        return forename;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Person)) return false;
        return this.toString().equalsIgnoreCase(other.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public int compareTo(Person that) {
        if (this == that) return 0;
        if (that == null) return 1;
        return this.toString().compareToIgnoreCase(that.toString());
    }

    @Override
    public String toString() {
      return forename + " " + surname;
    }

}
