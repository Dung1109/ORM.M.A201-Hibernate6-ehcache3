package org.hibernate.movietypeDAO;

import org.hibernate.entities.Movie;
import org.hibernate.entities.MovieType;
import org.hibernate.entities.Type;
import org.hibernate.movieDAO.MovieDAO;
import org.hibernate.movieDAO.MovieDAOImpl;
import org.hibernate.typeDAO.TypeDAO;
import org.hibernate.typeDAO.TypeDAOImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MovieTypeDAOImplTest {

    static MovieTypeDAO movieTypeDAO;
    static MovieDAO movieDAO;
    static TypeDAO typeDAO;

    @BeforeAll
    static void beforeAll() {
        movieTypeDAO = new MovieTypeDAOImpl();
        movieDAO = new MovieDAOImpl();
        typeDAO = new TypeDAOImpl();
    }

    @AfterEach
    void tearDown() {
        movieTypeDAO.deleteAllMovieTypes();
        movieDAO.deleteAllMovies();
        typeDAO.deleteAllTypes();
    }

    @Test
    void getMovieTypeByIdTest() {
        // create a movie and persist
        Movie movie = new Movie();
        movie.setMovieId("M0001");
        movie.setActor("Actor 1");
        movie.setContent("Content 1");
        movie.setDirector("Director 1");
        movie.setDuration(new BigDecimal(1.5));
        movie.setFromDate(LocalDate.now());
        movie.setToDate(LocalDate.now());
        movie.setMovieProductionCompany("Movie Production Company 1");
        movie.setVersion("Version 1");
        movie.setMovieNameEng("Movie Name Eng 1");
        movie.setMovieNameVie("Movie Name Vie 1");
        movie.setLargeImage("Large Image 1");
        movie.setSmallImage("Small Image 1");
        movieDAO.insertMovie(movie);

        // create a type and persist
        Type type = new Type();
        type.setTypeName("Action");
        type.setTypeDescription("Action movie");
        typeDAO.insertType(type);

        // create a movieType and persist
        MovieType movieType = new MovieType();
        movieType.setMovieId(movie);
        movieType.setTypeId(type);
        movieType.setMtDescription("Movie Type Description 1");
        movieTypeDAO.insertMovieType(movieType);

        MovieType movieType2 = movieTypeDAO.getMovieTypeById(movie, type);
        assertThat(movieType2.getMovieId().getMovieId()).isEqualTo("M0001");
        assertThat(movieType2.getTypeId().getTypeId()).isEqualTo(1);
        assertThat(movieType2.getMtDescription()).isEqualTo("Movie Type Description 1");
    }

    @Test
    void getAllMovieTypesTest() {
        // create a movie and persist
        Movie movie = new Movie();
        movie.setMovieId("M0001");
        movie.setActor("Actor 1");
        movie.setContent("Content 1");
        movie.setDirector("Director 1");
        movie.setDuration(new BigDecimal(1.5));
        movie.setFromDate(LocalDate.now());
        movie.setToDate(LocalDate.now());
        movie.setMovieProductionCompany("Movie Production Company 1");
        movie.setVersion("Version 1");
        movie.setMovieNameEng("Movie Name Eng 1");
        movie.setMovieNameVie("Movie Name Vie 1");
        movie.setLargeImage("Large Image 1");
        movie.setSmallImage("Small Image 1");
        movieDAO.insertMovie(movie);

        // create a type and persist
        Type type = new Type();
        type.setTypeName("Action");
        type.setTypeDescription("Action movie");
        typeDAO.insertType(type);

        // create a movieType and persist
        MovieType movieType = new MovieType();
        movieType.setMovieId(movie);
        movieType.setTypeId(type);
        movieType.setMtDescription("Movie Type Description 1");
        movieTypeDAO.insertMovieType(movieType);

        // create a movie and persist
        Movie movie2 = new Movie();
        movie2.setMovieId("M0002");
        movie2.setActor("Actor 2");
        movie2.setContent("Content 2");
        movie2.setDirector("Director 2");
        movie2.setDuration(new BigDecimal(2.5));
        movie2.setFromDate(LocalDate.now());
        movie2.setToDate(LocalDate.now());
        movie2.setMovieProductionCompany("Movie Production Company 2");
        movie2.setVersion("Version 2");
        movie2.setMovieNameEng("Movie Name Eng 2");
        movie2.setMovieNameVie("Movie Name Vie 2");
        movie2.setLargeImage("Large Image 2");
        movie2.setSmallImage("Small Image 2");
        movieDAO.insertMovie(movie2);

        // create a type and persist
        Type type2 = new Type();
        type2.setTypeName("Horror");
        type2.setTypeDescription("Horror movie");
        typeDAO.insertType(type2);

        // create a movieType and persist
        MovieType movieType2 = new MovieType();
        movieType2.setMovieId(movie2);
        movieType2.setTypeId(type2);
        movieType2.setMtDescription("Movie Type Description 2");
        movieTypeDAO.insertMovieType(movieType2);

        assertThat(movieTypeDAO.getAllMovieTypes().size()).isEqualTo(2);
    }

    @Test
    void updateMovieTypeByIdTest() {
        // create a movie and persist
        Movie movie = new Movie();
        movie.setMovieId("M0001");
        movie.setActor("Actor 1");
        movie.setContent("Content 1");
        movie.setDirector("Director 1");
        movie.setDuration(new BigDecimal(1.5));
        movie.setFromDate(LocalDate.now());
        movie.setToDate(LocalDate.now());
        movie.setMovieProductionCompany("Movie Production Company 1");
        movie.setVersion("Version 1");
        movie.setMovieNameEng("Movie Name Eng 1");
        movie.setMovieNameVie("Movie Name Vie 1");
        movie.setLargeImage("Large Image 1");
        movie.setSmallImage("Small Image 1");
        movieDAO.insertMovie(movie);

        // create a type and persist
        Type type = new Type();
        type.setTypeName("Action");
        type.setTypeDescription("Action movie");
        typeDAO.insertType(type);

        // create a movieType and persist
        MovieType movieType = new MovieType();
        movieType.setMovieId(movie);
        movieType.setTypeId(type);
        movieType.setMtDescription(movieType.getMovieId().getMovieNameEng() + " - " + movieType.getTypeId().getTypeName());
        movieTypeDAO.insertMovieType(movieType);

        Movie movie1 = movieDAO.getMovieById("M0001");
        // update
        assertDoesNotThrow(() -> movieTypeDAO.updateMovieTypeById(movie1, type));
        assertThat(movieTypeDAO.getMovieTypeById(movie1, type).getMtDescription()).isEqualTo("new description");

    }

    @Test
    void deleteMovieTypeByIdTest() {
        // create a movie and persist
        Movie movie = new Movie();
        movie.setMovieId("M0001");
        movie.setActor("Actor 1");
        movie.setContent("Content 1");
        movie.setDirector("Director 1");
        movie.setDuration(new BigDecimal(1.5));
        movie.setFromDate(LocalDate.now());
        movie.setToDate(LocalDate.now());
        movie.setMovieProductionCompany("Movie Production Company 1");
        movie.setVersion("Version 1");
        movie.setMovieNameEng("Movie Name Eng 1");
        movie.setMovieNameVie("Movie Name Vie 1");
        movie.setLargeImage("Large Image 1");
        movie.setSmallImage("Small Image 1");
        movieDAO.insertMovie(movie);

        // create a type and persist
        Type type = new Type();
        type.setTypeName("Action");
        type.setTypeDescription("Action movie");
        typeDAO.insertType(type);

        // create a movieType and persist
        MovieType movieType = new MovieType();
        movieType.setMovieId(movie);
        movieType.setTypeId(type);
        movieType.setMtDescription(movieType.getMovieId().getMovieNameEng() + " - " + movieType.getTypeId().getTypeName());
        movieTypeDAO.insertMovieType(movieType);

        // delete
        assertDoesNotThrow(() -> movieTypeDAO.deleteMovieTypeById(movie, type));
    }

    @Test
    void insertMovieTypeTest() {
        // create a movie and persist
        Movie movie = new Movie();
        movie.setMovieId("M0001");
        movie.setActor("Actor 1");
        movie.setContent("Content 1");
        movie.setDirector("Director 1");
        movie.setDuration(new BigDecimal(1.5));
        movie.setFromDate(LocalDate.now());
        movie.setToDate(LocalDate.now());
        movie.setMovieProductionCompany("Movie Production Company 1");
        movie.setVersion("Version 1");
        movie.setMovieNameEng("Movie Name Eng 1");
        movie.setMovieNameVie("Movie Name Vie 1");
        movie.setLargeImage("Large Image 1");
        movie.setSmallImage("Small Image 1");
        movieDAO.insertMovie(movie);

        // create a type and persist
        Type type = new Type();
        type.setTypeName("Action");
        type.setTypeDescription("Action movie");
        typeDAO.insertType(type);

        // create a movieType and persist
        MovieType movieType = new MovieType();
        movieType.setMovieId(movie);
        movieType.setTypeId(type);
        movieType.setMtDescription(movieType.getMovieId().getMovieNameEng() + " - " + movieType.getTypeId().getTypeName());
        assertDoesNotThrow(() -> movieTypeDAO.insertMovieType(movieType));
    }
}