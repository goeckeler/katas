package kata.lambdas.labs;

import static org.hamcrest.MatcherAssert.assertThat;

import kata.lambdas.model.Movies;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

public class Lab06 {
    @Test
    public void shouldCollectAllMoviesSeparatedByComma() {
        System.out.println("Lab06.shouldCollectAllMoviesSeparatedByComma");
        String line = "";

        assertThat(line, Matchers.equalTo("Notting Hill, The Mexican, The Unseen, Quindon Park, The Beach"));
    }
}
