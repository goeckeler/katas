package kata.lambdas.labs;

import kata.lambdas.domain.Movie;
import kata.lambdas.model.Movies;
import org.junit.Test;

public class Lab01 {
    @Test
    public void shouldListAllMoviesOnePerLine() {
        System.out.println("Lab01.shouldListAllMoviesOnePerLine");
    }

    @Test
    public void shouldListAllMoviesOnSingleLineWithSpaceInBetween() {
        System.out.println("Lab01.shouldListAllMoviesOnSingleLineWithSpaceInBetween");
        System.out.println();
    }

    @Test
    public void shouldListAllMoviesOnSingleLineInBracketsUsingStaticMethod() {
        System.out.println("Lab01.shouldListAllMoviesOnSingleLineInBracketsUsingStaticMethod");
        System.out.println();
    }

    private static void printMovie(Movie movie) {
        System.out.print("[" + movie.getTitle() + "] ");
    }
}
