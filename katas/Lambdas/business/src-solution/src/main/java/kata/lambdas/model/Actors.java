package kata.lambdas.model;

import kata.lambdas.domain.Actor;

import java.util.*;

public class Actors {
    public static Actor GEORGE_CLOONEY = new Actor("George", "Clooney");
    public static Actor JULIA_ROBERTS = new Actor("Julia", "Roberts");
    public static Actor HUGH_GRANT = new Actor("Hugh", "Grant");
    public static Actor BRAD_PITT = new Actor("Brad", "Pitt");
    public static Actor MILEY_CYRUS = new Actor("Miley", "Cyrus");

    private static Set<Actor> actors = new TreeSet<>();

    static {
        actors.add(GEORGE_CLOONEY);
        actors.add(JULIA_ROBERTS);
        actors.add(HUGH_GRANT);
        actors.add(BRAD_PITT);
        actors.add(MILEY_CYRUS);
    }

    public Set<Actor> list() {
        return Collections.unmodifiableSet(actors);
    }
}
