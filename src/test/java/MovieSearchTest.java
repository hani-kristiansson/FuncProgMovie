import mogodbDemo.atlas.Movie;
import mogodbDemo.atlas.MovieSearch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MovieSearchTest {

    MovieSearch x = new MovieSearch();

    public List<Movie> getTestMovies() {
        List<Movie> movies = new ArrayList<>(List.of(
                new Movie("100", "The Seventh Seal", 1975, List.of("horror"), "Ingmar Bergman", List.of("Alma", "Emil"),
                        2.4, List.of("english", "korean"), 54),
                new Movie("101", "Persona", 1975, List.of("thriller", "drama"), "Ingmar Bergman", List.of("Emil"),
                        9.5, List.of("english", "korean", "Swedish"), 62),
                new Movie("102", "Lord of the rings", 1975, List.of("action", "fantasy"), "Peter Jackson", List.of("Aragon", "Boromir", "Legolas"),
                        6, List.of("english", "ork", "swedish"), 120),
                new Movie("103", "Lord of the flies", 1975, List.of("action", "horror"), "Peter Jackson jr", List.of("Fly", "Legolas"),
                        5.3, List.of("english"), 25),
                new Movie("104", "Parasite", 1975, List.of("sci-fi"), "BongJunHo",List.of("Alma"),
                        8.2, List.of("english"), 200)
        ));
        return movies;
    }

    @Test
    public void countNumberOfMoviesTest() {
        List<Movie> movies = getTestMovies();
        assertEquals(5, x.countNumberOfMovies(movies));
        assertEquals(movies.size(), x.countNumberOfMovies(movies));
    }

    @Test
    public void longestRuntimeInMinutes() {
        List<Movie> movies = getTestMovies();
        assertEquals(200, x.longestRuntimeInMinutes(movies));
        assertNotEquals(120, x.longestRuntimeInMinutes(movies));
    }

    @Test
    public void countNumberOfGenrer() {
        List<Movie> movies = getTestMovies();
        assertEquals(5, x.countNumberOfGenrer(movies));
    }

    @Test
    public void actorsInMovieWithHighestRating(){
        List<Movie> movies = getTestMovies();
        List<String> actors = x.actorsInMovieWithHighestRating(movies); //return type : list of actors
        assertEquals(1, actors.size());
        assertTrue(actors.contains("Emil"));
        assertFalse(actors.contains("Emilia"));
    }

    @Test
    public void getMovieTitleWhereSmallestCast(){
        List<Movie> movies = getTestMovies();
        String title = x.getMovieTitleWhereSmallestCast(movies);
        assertEquals("Persona", title);
        //assertEquals("Parasite", title);
    }

    @Test
    public void countActorsInMoreThanOneMovie(){
        List<Movie> movies = getTestMovies();
    }

    @Test
    public void actorWithMostMovies(){
        List<Movie> movies = getTestMovies();
    }

    @Test
    public void countNumberOfLanguages(){
        List<Movie> movies = getTestMovies();
        assertEquals(5, x.countNumberOfLanguages(movies));
    }

    @Test
    public void existsSameTitle(){
        List<Movie> movies = getTestMovies();
        //assertTrue(x.existsSameTitle(movies));     ??????   BOOLEAN
    }
}

/*
        assertTrue(allQs.contains("Huvudingrediensen i risotto?"));
        assertEquals(allQs.size(), 6);
    }
 */