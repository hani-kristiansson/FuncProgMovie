package mogodbDemo.atlas;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;


public class Main {

    private final MovieSearch movieSearch = new MovieSearch();

    public Main() {

        String uri = "mongodb+srv://test:test@cluster0.xfxbh.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Get all movies from 1975
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

            // Skriver ut alla filmer
//            for (Movie movie : movieList) {
//                System.out.println(movie);
//            }

            ////TODO Här gör du anrop till alla dina funktioner som ska skriva ut svaren på frågorna som efterfrågas i uppgiften
            int numberOfMovies = movieSearch.countNumberOfMovies(movieList);
            System.out.println("Number of movies in 1975: " + numberOfMovies);

            int longestRuntimeInMinutes = movieSearch.longestRuntimeInMinutes(movieList);
            System.out.println("Longest runtime in Minutes: " + longestRuntimeInMinutes);

            int numberOfGenrer = movieSearch.countNumberOfGenrer(movieList);
            System.out.println("Number of genrers: " + numberOfGenrer);

            ///v1
            List<String> actorsInMovieWithHighestRating= movieSearch.actorsInMovieWithHighestRating(movieList);
            System.out.println("Actors in movie with highest rating: " + actorsInMovieWithHighestRating);

            ///v2 with Hof
            Comparator<Movie> imdbRatingComparator =
                    ((movie1, movie2) ->((Double) movie1.getImdbRating()).compareTo(movie2.getImdbRating()));

            List<String> actorsInMovieWithHighestRatingHof= movieSearch.actorsInMovieWithHighestRatingHof(movieList, imdbRatingComparator);
            System.out.println("Actors in movie with highest rating with Hof: " + actorsInMovieWithHighestRatingHof);


            String movieTitleWhereSmallestCast = movieSearch.getMovieTitleWhereSmallestCast(movieList);
            System.out.println("Movie title with smallest cast: " + movieTitleWhereSmallestCast);

            int actorsInMoreThanOneMovie = movieSearch.countActorsInMoreThanOneMovie(movieList);
            System.out.println("Number of actors in more than one movie: " + actorsInMoreThanOneMovie);

            List<String> actorsInMostMovies= movieSearch.actorsInMostMovies(movieList);
            System.out.println("Actors in most movies: " + actorsInMostMovies);

            ///v1
            int numberOfLanguages = movieSearch.countNumberOfLanguages(movieList);
            System.out.println("Number of languages: " + numberOfLanguages);

            ///v2 with Hof
            Function<Movie, Stream<String>> languageMapper = m -> m.getLanguages().stream();

            //public int countNumberOfUniqueStringHof(List<Movie> movies, Function<Movie, Stream<String>> mapper)
            int numberOfLanguagesHof = movieSearch.countNumberOfUniqueStringHof(movieList, languageMapper);
            System.out.println("Number of languages with hof: " + numberOfLanguagesHof);


            //TODO count number of distinct actor
            Function<Movie, Stream<String>> actorMapper = m -> m.getCast().stream();

            int numberOfActorHof = movieSearch.countNumberOfUniqueStringHof(movieList, actorMapper);
            System.out.println("Number of actors with hof: " + numberOfActorHof);


            //TODO count number of distinct genrer
            Function<Movie, Stream<String>> genrerMapper = m -> m.getGenres().stream();

            int numberOfGenrerHof = movieSearch.countNumberOfUniqueStringHof(movieList, genrerMapper);
            System.out.println("Number of genrer with hof: " + numberOfGenrerHof);


            boolean existsSameTitle= movieSearch.existsSameTitle(movieList);
            System.out.println("Exists same title: " + existsSameTitle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}