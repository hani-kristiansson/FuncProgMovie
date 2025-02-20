package mogodbDemo.atlas;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieSearch {

    //Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal
    // casted to int because .count return type is long
    public int countNumberOfMovies(List<Movie> movies) {
        return (int) movies.stream().count();
    }

    //Hitta längden på den film som var längst (högst runtime). Returnera ett tal.
    //if it is stream then to use max, need to use comparator
    public int longestRuntimeInMinutes(List<Movie> movies) {
        return movies.stream()
                .mapToInt(movie -> movie.getRuntime())
                .max()
                .getAsInt(); //optional can have value in it or null so it needs this method to get int value
    }

    //Hur många UNIKA genrer hade filmerna från 1975. Returnera ett tal.
    //movie.getGenres() return List, then need to .stream() to convert "from list to stream"
    public int countNumberOfGenrer(List<Movie> movies) {
        return (int) movies.stream()
                .flatMap(movie -> movie.getGenres().stream())
                .distinct()
                .count();
    }

    //Vilka skådisar som spelade i den film som hade högst imdb-rating. Returnera en List<String> med deras namn.
    //compareTo can not be used with primitive type
    public List<String> actorsInMovieWithHighestRating(List<Movie> movies) {
        return movies.stream()
                .max((m1, m2) -> ((Double) m1.getImdbRating()).compareTo(m2.getImdbRating()))
                .map(s -> s.getCast())
                .orElse(null);
    }

    //TODO with Hof
    public List<String> actorsInMovieWithHighestRatingHof(List<Movie> movies, Comparator<Movie> comparator) {
        return movies.stream()
                .max((m1, m2) -> comparator.compare(m1, m2))
                .map(s -> s.getCast())
                .orElse(null);
    }

    //Vad är titeln på den film som hade minst antal skådisar listade? Returnera en String.
    //compareTo can be only used with referenceType so need to use Integer
    public String getMovieTitleWhereSmallestCast(List<Movie> movies) {
        return movies.stream()
                .min((m1, m2) -> ((Integer) m1.getCast().size()).compareTo(m2.getCast().size()))
                .map(s -> s.getTitle())
                .orElse(null);
    }

    //Hur många skådisar var med i mer än 1 film? Returnera ett tal.
    public int countActorsInMoreThanOneMovie(List<Movie> movies) {
        return (int) movies.stream()
                .flatMap(movie-> movie.getCast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))
                .values()
                .stream()
                .filter( l -> l > 1)
                .count();
    }

    //Vad hette den skådis som var med i flest filmer? Returnera en String eller list
    public List<String> actorsInMostMovies(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie -> movie.getCast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))
                .entrySet()
                //[Entry("Jimmy", 10), ("Jennifer, 5)]
                .stream()

                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                // 10 -> ["Jimmy", "actor3"]
                .entrySet()
                .stream()

                .max((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey()))
                .map(Map.Entry::getValue)
                .orElse(null);
    }

    static String actorsInMostMovies2(List<Movie> movies) {
        return movies.stream()
                .flatMap(m -> m.getCast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))
                .entrySet()
                //[Entry("Jimmy", 10), ("Jennifer, 5)]
                .stream()
                .max((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    //Hur många UNIKA språk har filmerna? Returnera ett tal.
    public int countNumberOfLanguages(List<Movie> movies) {
        return (int) movies.stream()
                .flatMap(movie -> movie.getLanguages().stream())
                .distinct()
                .count();
    }

    //TODO Hof
    //replace map and flatmap with custom mapper
    // s -> s.getLanguages().stream())
    public int countNumberOfUniqueStringHof(List<Movie> movies, Function<Movie, Stream<String>> mapper) {
        return (int) movies.stream()
                .flatMap(movie -> mapper.apply(movie))
                .distinct()
                .count();
    }

    //Finns det någon titel som mer än en film har? Returnera en bool.
    public boolean existsSameTitle(List<Movie> movies) {
        return movies.stream()
                .collect(Collectors.groupingBy(movie -> movie.getTitle(), Collectors.counting()))
                .values()
                .stream()
                .anyMatch(l -> l > 1);
    }
}
