package org.hibernate.movieDAO;

import org.hibernate.entities.Movie;

import java.util.List;

public interface MovieDAO {
    Movie getMovieById(String id);
    List<Movie> getAllMovies();
    void updateMovieById(String id, Movie movie);
    void deleteMovieById(String id);
    void insertMovie(Movie movie);
    void deleteAllMovies();
}
