package kata.lambdas.labs;

import kata.lambdas.model.Actors;
import kata.lambdas.model.Movies;
import org.junit.Test;

public class Lab03 {
    @Test
    public void shouldListAllMoviesWithAtLeastTwoActors() {
        System.out.println("Lab03.shouldListAllMoviesWithAtLeastTwoActors");
        Movies.list().stream().filter(movie -> movie.getActors().size() >= 2).forEach(System.out::println);
    }

    @Test
    public void shouldCountActorsOfMovies() {
        System.out.println("Lab03.shouldCountActorsOfMovies");
        Movies.list().stream().mapToInt(movie -> movie.getActors().size()).forEach(System.out::println);
    }
}
