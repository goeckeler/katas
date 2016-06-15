package kata.lambdas.domain;

import java.util.Set;
import java.util.TreeSet;

public class Movie implements Comparable<Movie> {
    private String title;
    private Set<Actor> actors = new TreeSet<>();

    public Movie(String title) {
        assert title != null;
        this.title = title;
    }

    public Movie with(Actor actor) {
        actors.add(actor);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Movie)) return false;
        return this.toString().equalsIgnoreCase(other.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public int compareTo(final Movie that) {
        if (this == that) return 0;
        if (that == null) return 1;
        return this.toString().compareToIgnoreCase(that.toString());
    }

    @Override
    public String toString() {
        return  title;
    }
}
