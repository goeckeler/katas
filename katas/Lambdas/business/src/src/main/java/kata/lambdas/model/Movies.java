package kata.lambdas.model;

import kata.lambdas.domain.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Movies {
    private static List<Movie> movies = new ArrayList<>();

    public static final Movie NOTTING_HILL = new Movie("Notting Hill").with(Actors.JULIA_ROBERTS).with(Actors.HUGH_GRANT).with(Actors.GEORGE_CLOONEY);
    public static final Movie THE_MEXICAN = new Movie("The Mexican").with(Actors.JULIA_ROBERTS).with(Actors.GEORGE_CLOONEY);
    public static final Movie THE_UNSEEN = new Movie("The Unseen").with(Actors.GEORGE_CLOONEY).with(Actors.BRAD_PITT);
    public static final Movie QUINDON_PARK = new Movie("Quindon Park").with(Actors.HUGH_GRANT).with(Actors.JULIA_ROBERTS);
    public static final Movie THE_BEACH = new Movie("The Beach").with(Actors.BRAD_PITT);

    static {
        movies.add(NOTTING_HILL);
        movies.add(THE_MEXICAN);
        movies.add(THE_UNSEEN);
        movies.add(QUINDON_PARK);
        movies.add(THE_BEACH);
    }

    public static List<Movie> list() {
        return Collections.unmodifiableList(movies);
    }
}
