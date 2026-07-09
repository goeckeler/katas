package kata.lambdas.labs;

import static org.hamcrest.MatcherAssert.assertThat;

import kata.lambdas.model.Movies;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class Lab05 {
    @Test
    public void shouldCountAllTitleCharactersOfMovies() {
        System.out.println("Lab05.shouldCountAllTitleCharactersOfMovies");
        long characters = 0;
        System.out.println(String.format("There are %1$d characters in all movie titles.", characters));

        characters = 0;
        System.out.println(String.format("There are still %1$d characters in all movie titles.", characters));
    }
}
