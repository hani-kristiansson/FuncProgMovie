package mogodbDemo.atlas;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class MongoDBAtlasDownloadExample {

    private final MovieSearch movieSearch = new MovieSearch();

    public MongoDBAtlasDownloadExample() {

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
            System.out.println();

            int longestRuntimeInMinutes = movieSearch.longestRuntimeInMinutes(movieList);
            System.out.println("Longest runtime in Minutes: " + longestRuntimeInMinutes);
            System.out.println();

            int numberOfGenrer = movieSearch.countNumberOfGenrer(movieList);
            System.out.println("Number of genrers: " + numberOfGenrer);
            System.out.println();

            List<String> actorsInMovieWithHighestRating= movieSearch.actorsInMovieWithHighestRating(movieList);
            System.out.println("Actors in movie with highest rating: " + actorsInMovieWithHighestRating);
            System.out.println();

            String movieTitleWhereSmallestCast = movieSearch.getMovieTitleWhereSmallestCast(movieList);
            System.out.println("Movie title with smallest cast: " + movieTitleWhereSmallestCast);
            System.out.println();

            int numberOfLanguages = movieSearch.countNumberOfLanguages(movieList);
            System.out.println("Number of languages: " + numberOfLanguages);
            System.out.println();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MongoDBAtlasDownloadExample m = new MongoDBAtlasDownloadExample();
    }
}