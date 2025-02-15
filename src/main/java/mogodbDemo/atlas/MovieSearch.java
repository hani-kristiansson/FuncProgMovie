package mogodbDemo.atlas;

import java.util.List;

public class MovieSearch {

    //Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal
    public int countNumberOfMovies(List<Movie> movies) {
        return (int)movies.stream().count();
    }

    //Hitta längden på den film som var längst (högst runtime). Returnera ett tal.
    public int longestRuntimeInMinutes(List<Movie> movies) {
        return movies.stream().mapToInt(s->s.getRuntime()).max().getAsInt();
    }

    //Hur många UNIKA genrer hade filmerna från 1975. Returnera ett tal.
    public int countNumberOfGenrer(List<Movie> movies){
        return (int) movies.stream().map(s -> s.getGenres()).distinct().count();
    }

    //Vilka skådisar som spelade i den film som hade högst imdb-rating. Returnera en List<String> med deras namn.
    public List<String> actorsInMovieWithHighestRating(List<Movie> movies){
        return movies.stream()
                .max((m1, m2) -> ((Double)m1.getImdbRating()).compareTo(m2.getImdbRating()))
                .map(s -> s.getCast()).orElse(null);
    }

    //Vad är titeln på den film som hade minst antal skådisar listade? Returnera en String.
    //compareTo can be only used with referenceType
    public String getMovieTitleWhereSmallestCast(List<Movie> movies){
        return movies.stream()
                .min((m1, m2) -> ((Integer) m1.getCast().size()).compareTo(m2.getCast().size()))
                .map(s -> s.getTitle()).orElse(null);
    }

    //Hur många skådisar var med i mer än 1 film? Returnera ett tal.



    //Vad hette den skådis som var med i flest filmer? Returnera en String



    //Hur många UNIKA språk har filmerna? Returnera ett tal.
    public int countNumberOfLanguages(List<Movie> movies){
        return (int) movies.stream().map(s -> s.getLanguages())
                .flatMap(s -> s.stream())
                .distinct()
                .count();
    }


    //Finns det någon titel som mer än en film har? Returnera en bool.
    public boolean existsSameTitle(List<Movie> movies){
        //return movies.stream().
        throw new UnsupportedOperationException();
    }



}