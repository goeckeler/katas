package kata.lambdas.labs;

import kata.lambdas.domain.Movie;
import kata.lambdas.domain.User;
import kata.lambdas.model.Movies;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static kata.lambdas.model.Movies.*;
import static kata.lambdas.model.Users.JANE_DOE;
import static kata.lambdas.model.Users.JOHN_DOE;
import static org.junit.Assert.assertThat;


public class Lab07 {
    /**
     * Now this test is tricky ... a user likes certain actors, and now we are saying if most of his or her
     * favorite actors are playing in the same movie, that would be the next movie to watch.
     * <p>
     * Please rank all movies according to a user's preference, and list all movies the user might want to watch.
     * Bonus: Add the rank to the movies on the fly.
     */
    @Test
    public void shouldRecommendMovies() {
        System.out.println("Lab07.shouldRecommendMovies");
        // check that the correct movies are found in the correct order
        assertThat("Incorrect ranking of Jane's favorites", recommendMoviesFor(JANE_DOE), Matchers.contains(NOTTING_HILL, QUINDON_PARK, THE_MEXICAN, THE_UNSEEN));

        // check that the correct movies are found in the correct order
        assertThat("Incorrect ranking of John's favorites", recommendMoviesFor(JOHN_DOE), Matchers.contains(THE_UNSEEN, THE_BEACH, THE_MEXICAN, NOTTING_HILL));
        // check that the correct movies are found in the correct order
    }

    public List<Movie> recommendMoviesFor(final User user) {
        // first find out how many actors are favored in a particular movie if any
        // @formatter:off
        final Function<Movie, Long> numberOfFavoriteActorsInMovie =
          movie -> movie.getActors().stream()
                        .filter(user::isFavoriteActor)
                        .count()
                        ;
        // @formatter:on
        final Comparator<Movie> sortByFavorites = Comparator.comparingLong(numberOfFavoriteActorsInMovie::apply).reversed();

        // the more favorites the better as they are likely to be longer on screen
        final Comparator<Movie> sortByScreenPresence = Comparator.comparingInt(a -> a.getActors().size());

        // @formatter:off
        return Movies.list().parallelStream()
                     .filter(movie -> movie.getActors().stream().anyMatch(user::isFavoriteActor)) 
                     .sorted(
                        sortByFavorites
                        .thenComparing(sortByScreenPresence)
                        .thenComparing(Comparator.naturalOrder())
                     )
                     .collect(Collectors.toList());
        // @formatter:on
    }
}
