package kata.lambdas.labs;

import kata.lambdas.domain.Movie;
import kata.lambdas.model.Movies;
import org.junit.Test;

public class Lab01 {
    @Test
    public void shouldListAllMoviesOnePerLine() {
        System.out.println("Lab01.shouldListAllMoviesOnePerLine");
        Movies.list().forEach(System.out::println);
    }

    @Test
    public void shouldListAllMoviesOnSingleLineWithSpaceInBetween() {
        System.out.println("Lab01.shouldListAllMoviesOnSingleLineWithSpaceInBetween");
        Movies.list().forEach(movie -> System.out.print(movie.toString() + " "));
        System.out.println();
    }

    @Test
    public void shouldListAllMoviesOnSingleLineInBracketsUsingStaticMethod() {
        System.out.println("Lab01.shouldListAllMoviesOnSingleLineInBracketsUsingStaticMethod");
        Movies.list().parallelStream().forEach(Lab01::printMovie);
        System.out.println();
    }

    private static void printMovie(Movie movie) {
        System.out.print("[" + movie.getTitle() + "] ");
    }
}
