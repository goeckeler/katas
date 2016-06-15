package kata.lambdas.domain;

import java.util.Set;
import java.util.TreeSet;

public class User extends Person {
    private Set<Actor> favoriteActors = new TreeSet<>();

    public User(String forename, String surname) {
        super(forename, surname);
    }

    public User likes(Actor actor) {
        assert actor != null;
        favoriteActors.add(actor);
        return this;
    }

    public Set<Actor> getFavoriteActors() {
        return favoriteActors;
    }

    public boolean isFavoriteActor(Actor actor) {
        return favoriteActors.contains(actor);
    }
}
