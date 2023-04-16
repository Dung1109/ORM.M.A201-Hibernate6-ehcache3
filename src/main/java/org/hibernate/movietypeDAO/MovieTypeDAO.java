package org.hibernate.movietypeDAO;

import org.hibernate.entities.Movie;
import org.hibernate.entities.MovieType;
import org.hibernate.entities.Type;

import java.util.List;

public interface MovieTypeDAO {
    MovieType getMovieTypeById(Movie movie, Type type);
    List<MovieType> getAllMovieTypes();
    void updateMovieTypeById(Movie movie, Type type);
    void deleteMovieTypeById(Movie movie, Type type);
    void insertMovieType(MovieType movieType);
    void deleteAllMovieTypes();
}
