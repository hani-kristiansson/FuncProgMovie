import mogodbDemo.atlas.Movie;
import mogodbDemo.atlas.MovieSearch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MovieSearchTest {

    MovieSearch x = new MovieSearch();

    public List<Movie> getTestMovies() {
        List<Movie> movies = new ArrayList<>(List.of(
                new Movie("100", "The Seventh Seal", 1975, List.of("horror"),
                        "Ingmar Bergman", List.of("Alma", "Emil"),
                        2.4, List.of("english", "korean"), 54),
                new Movie("101", "Persona", 1975, List.of("thriller", "drama"),
                        "Ingmar Bergman", List.of("Emil"),
                        9.5, List.of("english", "korean", "Swedish"), 62),
                new Movie("102", "Lord of the rings", 1975, List.of("action", "fantasy"),
                        "Peter Jackson", List.of("Aragon", "Boromir", "Legolas"),
                        6, List.of("english", "ork", "swedish"), 120),
                new Movie("103", "Lord of the flies", 1975, List.of("action", "horror"),
                        "Peter Jackson jr", List.of("Fly", "Legolas"),
                        5.3, List.of("english"), 25),
                new Movie("104", "Parasite", 1975, List.of("sci-fi"),
                        "BongJunHo",List.of("Alma"),
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
        assertEquals(6, x.countNumberOfGenrer(movies));
        assertNotEquals(7, x.countNumberOfGenrer(movies));
    }

    @Test
    public void actorsInMovieWithHighestRating(){
        List<Movie> movies = getTestMovies();

        List<String> actors = x.actorsInMovieWithHighestRating(movies); //return type : list of actors
        assertEquals(1, actors.size());

        assertTrue(actors.contains("Emil"));
        assertFalse(actors.contains("Emma"));
    }

    @Test
    public void actorsInMovieWithHighestRatingHof(){

        Comparator<Movie> imdbRatingComparator =
                ((movie1, movie2) ->((Double) movie1.getImdbRating()).compareTo(movie2.getImdbRating()));

        List<Movie> movies = getTestMovies();
        List<String> actors = x.actorsInMovieWithHighestRatingHof(movies,imdbRatingComparator); //return type : list of actors
        assertEquals(1, actors.size());
        assertTrue(actors.contains("Emil"));
        assertFalse(actors.contains("Emma"));
    }

    @Test
    public void getMovieTitleWhereSmallestCast(){
        List<Movie> movies = getTestMovies();
        String title = x.getMovieTitleWhereSmallestCast(movies);
        assertEquals("Persona", title);
        //assertEquals("Parasite", title); det blir fel för att i method, datatyp är sträng. så returnerar bara en film
    }

    @Test
    public void countActorsInMoreThanOneMovie(){
        List<Movie> movies = getTestMovies();
        int numberOfActorsInMoreThanOneMovie = x.countActorsInMoreThanOneMovie(movies);
        assertEquals(3, numberOfActorsInMoreThanOneMovie);
        assertNotEquals(0, numberOfActorsInMoreThanOneMovie);
    }

    @Test
    public void actorsInMostMovies(){
        List<Movie> movies = getTestMovies();
        List<String> actors = x.actorsInMostMovies(movies);
        assertEquals(3, actors.size());
        assertTrue(actors.contains("Emil"));
        assertTrue(actors.contains("Alma"));
        assertTrue(actors.contains("Legolas"));
    }

    @Test
    public void countNumberOfLanguages(){
        List<Movie> movies = getTestMovies();
        assertEquals(5, x.countNumberOfLanguages(movies));
        assertNotEquals(2, x.countNumberOfLanguages(movies));
    }

    @Test
    public void countNumberOfLanguagesHof(){
        Function<Movie, Stream<String>> mapper = m -> m.getLanguages().stream();

        List<Movie> movies = getTestMovies();

        assertEquals(5, x.countNumberOfUniqueStringHof(movies, mapper));
        assertNotEquals(2, x.countNumberOfUniqueStringHof(movies, mapper));
    }

    @Test
    public void existsSameTitle(){
        List<Movie> movies = getTestMovies();
        assertFalse(x.existsSameTitle(movies)); //test data do not have movies with same title
    }
}