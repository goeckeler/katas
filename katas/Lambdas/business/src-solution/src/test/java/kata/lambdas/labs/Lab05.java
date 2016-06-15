package kata.lambdas.labs;

import kata.lambdas.model.Movies;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class Lab05 {
    @Test
    public void shouldCountAllTitleCharactersOfMovies() {
        System.out.println("Lab05.shouldCountAllTitleCharactersOfMovies");
        long characters = Movies.list().stream().mapToLong(movie -> movie.getTitle().length()).sum();
        System.out.println(String.format("There are %1$d characters in all movie titles.", characters));

        characters = Movies.list().stream().mapToLong(movie -> movie.getTitle().length()).reduce(0L, (l,r) -> l + r);
        System.out.println(String.format("There are still %1$d characters in all movie titles.", characters));
    }
}
