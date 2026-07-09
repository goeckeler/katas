package kata.lambdas.labs;

import static org.hamcrest.MatcherAssert.assertThat;

import kata.lambdas.model.Movies;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class Lab04 {
    @Test
    public void shouldCountAllActingActorsOfMovies() {
        System.out.println("Lab04.shouldCountAllActingActorsOfMovies");
        assertThat(0L, Matchers.equalTo(4l));
    }

    @Test
    public void shouldListAllActingActorsOfMovies() {
        System.out.println("Lab04.shouldListAllActingActorsOfMovies");
    }


    @Test
    public void shouldListAllActingActorsOfMoviesInAlphabeticOrder() {
        System.out.println("Lab04.shouldListAllActingActorsOfMoviesInAlphabeticOrder");
    }
}
