package kata.lambdas.labs;

import static  org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import kata.lambdas.model.Movies;
import org.hamcrest.Matchers;
import org.junit.Test;

public class Lab04 {
    @Test
    public void shouldCountAllActingActorsOfMovies() {
        System.out.println("Lab04.shouldCountAllActingActorsOfMovies");
        assertThat(Movies.list().stream().flatMap(movie -> movie.getActors().stream()).distinct().count(), Matchers.equalTo(4l));
    }

    @Test
    public void shouldListAllActingActorsOfMovies() {
        System.out.println("Lab04.shouldListAllActingActorsOfMovies");
        Movies.list().stream().flatMap(movie -> movie.getActors().stream()).distinct().forEach(System.out::println);
    }


    @Test
    public void shouldListAllActingActorsOfMoviesInAlphabeticOrder() {
        System.out.println("Lab04.shouldListAllActingActorsOfMoviesInAlphabeticOrder");
        Movies.list().stream().flatMap(movie -> movie.getActors().stream()).distinct().sorted().forEach(System.out::println);
    }
}
