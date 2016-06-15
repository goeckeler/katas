package kata.lambdas.labs;

import kata.lambdas.model.Actors;
import kata.lambdas.model.Movies;
import org.junit.Test;

public class Lab02 {
    @Test
    public void shouldListAllMoviesWithHughGrant() {
        System.out.println("Lab02.shouldListAllMoviesWithHughGrant");
        Movies.list().parallelStream().filter(movie -> movie.getActors().contains(Actors.HUGH_GRANT)).forEach(System.out::println);
    }
}