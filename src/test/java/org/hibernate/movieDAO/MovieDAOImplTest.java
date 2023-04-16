package org.hibernate.movieDAO;

import org.hibernate.entities.Movie;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MovieDAOImplTest {

    static MovieDAO movieDAO;

    @BeforeAll
    static void beforeAll() {
        movieDAO = new MovieDAOImpl();
    }

    @AfterEach
    void afterEach() {
        // flush all data
        movieDAO.deleteAllMovies();
    }

    @Test
    void getMovieByIdTest() {
        Movie movie = new Movie();
        movie.setMovieId("M0001");
        movie.setActor("ACTOR_01");
        movie.setContent("CONTENT_01");
        movie.setDirector("DIRECTOR_01");
        movie.setDuration(BigDecimal.valueOf(1.01));
        movie.setFromDate(LocalDate.of(2021, 1, 1));
        movie.setToDate(LocalDate.of(2021, 1, 1));
        movie.setMovieProductionCompany("MOVIE_PRODUCTION_COMPANY_01");
        movie.setVersion("VERSION_01");
        movie.setMovieNameEng("MOVIE_NAME_ENG_01");
        movie.setMovieNameVie("MOVIE_NAME_VIE_01");
        movie.setLargeImage("Large_Image_01");
        movie.setSmallImage("Small_Image_01");

        movieDAO.insertMovie(movie);

        Movie movie2 = movieDAO.getMovieById("M0001");
        assertThat(movie2.getMovieId()).isEqualTo("M0001");
    }

    @Test
    void getAllMoviesTest() {
        Movie movie = new Movie();
        movie.setMovieId("M0001");
        movie.setActor("ACTOR_01");
        movie.setContent("CONTENT_01");
        movie.setDirector("DIRECTOR_01");
        movie.setDuration(BigDecimal.valueOf(1.01));
        movie.setFromDate(LocalDate.of(2021, 1, 1));
        movie.setToDate(LocalDate.of(2021, 1, 1));
        movie.setMovieProductionCompany("MOVIE_PRODUCTION_COMPANY_01");
        movie.setVersion("VERSION_01");
        movie.setMovieNameEng("MOVIE_NAME_ENG_01");
        movie.setMovieNameVie("MOVIE_NAME_VIE_01");
        movie.setLargeImage("Large_Image_01");
        movie.setSmallImage("Small_Image_01");

        movieDAO.insertMovie(movie);

        Movie movie2 = new Movie();
        movie2.setMovieId("M0002");
        movie2.setActor("ACTOR_02");
        movie2.setContent("CONTENT_02");
        movie2.setDirector("DIRECTOR_02");
        movie2.setDuration(BigDecimal.valueOf(2.02));
        movie2.setFromDate(LocalDate.of(2021, 1, 1));
        movie2.setToDate(LocalDate.of(2021, 1, 1));
        movie2.setMovieProductionCompany("MOVIE_PRODUCTION_COMPANY_02");
        movie2.setVersion("VERSION_02");
        movie2.setMovieNameEng("MOVIE_NAME_ENG_02");
        movie2.setMovieNameVie("MOVIE_NAME_VIE_02");
        movie2.setLargeImage("Large_Image_02");
        movie2.setSmallImage("Small_Image_02");

        movieDAO.insertMovie(movie2);

        assertThat(movieDAO.getAllMovies().size()).isEqualTo(2);
    }

    @Test
    void updateMovieByIdTest() {
        Movie movie = new Movie();
        movie.setMovieId("M0001");
        movie.setActor("ACTOR_01");
        movie.setContent("CONTENT_01");
        movie.setDirector("DIRECTOR_01");
        movie.setDuration(BigDecimal.valueOf(1.01));
        movie.setFromDate(LocalDate.of(2021, 1, 1));
        movie.setToDate(LocalDate.of(2021, 1, 1));
        movie.setMovieProductionCompany("MOVIE_PRODUCTION_COMPANY_01");
        movie.setVersion("VERSION_01");
        movie.setMovieNameEng("MOVIE_NAME_ENG_01");
        movie.setMovieNameVie("MOVIE_NAME_VIE_01");
        movie.setLargeImage("Large_Image_01");
        movie.setSmallImage("Small_Image_01");

        movieDAO.insertMovie(movie);

        Movie movie2 = movieDAO.getMovieById("M0001");
        assertThat(movie2.getMovieId()).isEqualTo("M0001");

        movie2.setActor("ACTOR_02");
        movie2.setContent("CONTENT_02");
        movie2.setDirector("DIRECTOR_02");
        movie2.setDuration(BigDecimal.valueOf(2.02));
        movie2.setFromDate(LocalDate.of(2021, 1, 1));
        movie2.setToDate(LocalDate.of(2021, 1, 1));
        movie2.setMovieProductionCompany("MOVIE_PRODUCTION_COMPANY_02");
        movie2.setVersion("VERSION_02");
        movie2.setMovieNameEng("MOVIE_NAME_ENG_02");
        movie2.setMovieNameVie("MOVIE_NAME_VIE_02");
        movie2.setLargeImage("Large_Image_02");
        movie2.setSmallImage("Small_Image_02");

        movieDAO.updateMovieById("M0001", movie2);

        Movie movie3 = movieDAO.getMovieById("M0001");
        assertAll(
                () -> assertThat(movie3.getMovieId()).isEqualTo("M0001"),
                () -> assertThat(movie3.getActor()).isEqualTo("ACTOR_02"),
                () -> assertThat(movie3.getContent()).isEqualTo("CONTENT_02"),
                () -> assertThat(movie3.getDirector()).isEqualTo("DIRECTOR_02"),
                () -> assertThat(movie3.getDuration()).isEqualTo(BigDecimal.valueOf(2.02)),
                () -> assertThat(movie3.getFromDate()).isEqualTo(LocalDate.of(2021, 1, 1)),
                () -> assertThat(movie3.getToDate()).isEqualTo(LocalDate.of(2021, 1, 1)),
                () -> assertThat(movie3.getMovieProductionCompany()).isEqualTo("MOVIE_PRODUCTION_COMPANY_02"),
                () -> assertThat(movie3.getVersion()).isEqualTo("VERSION_02"),
                () -> assertThat(movie3.getMovieNameEng()).isEqualTo("MOVIE_NAME_ENG_02"),
                () -> assertThat(movie3.getMovieNameVie()).isEqualTo("MOVIE_NAME_VIE_02"),
                () -> assertThat(movie3.getLargeImage()).isEqualTo("Large_Image_02"),
                () -> assertThat(movie3.getSmallImage()).isEqualTo("Small_Image_02")

        );

    }

    @Test
    void deleteMovieByIdTest() {
        Movie movie = new Movie();
        movie.setMovieId("M0001");
        movie.setActor("ACTOR_01");
        movie.setContent("CONTENT_01");
        movie.setDirector("DIRECTOR_01");
        movie.setDuration(BigDecimal.valueOf(1.01));
        movie.setFromDate(LocalDate.of(2021, 1, 1));
        movie.setToDate(LocalDate.of(2021, 1, 1));
        movie.setMovieProductionCompany("MOVIE_PRODUCTION_COMPANY_01");
        movie.setVersion("VERSION_01");
        movie.setMovieNameEng("MOVIE_NAME_ENG_01");
        movie.setMovieNameVie("MOVIE_NAME_VIE_01");
        movie.setLargeImage("Large_Image_01");
        movie.setSmallImage("Small_Image_01");

        movieDAO.insertMovie(movie);

        Movie movie2 = movieDAO.getMovieById("M0001");
        assertThat(movie2.getMovieId()).isEqualTo("M0001");

        movieDAO.deleteMovieById("M0001");

        assertThat(movieDAO.getMovieById("M0001")).isNull();
    }

    @Test
    void insertMovieTest() {
        Movie movie = new Movie();
        /*
        *  @Id
    @Column(name = "MOVIE_ID", nullable = false, length = 10)
    private String movieId;

    @Column(name = "ACTOR", nullable = false)
    private String actor;

    @Column(name = "CONTENT", nullable = false, length = 1000)
    private String content;

    @Column(name = "DIRECTOR", nullable = false)
    private String director;

    @Column(name = "DURATION", nullable = false, precision = 5, scale = 2)
    private BigDecimal duration;

    @Column(name = "FROM_DATE", nullable = false)
    private LocalDate fromDate;

    @Column(name = "TO_DATE", nullable = false)
    private LocalDate toDate;

    @Column(name = "MOVIE_PRODUCTION_COMPANY", nullable = false)
    private String movieProductionCompany;

    @Column(name = "VERSION", nullable = false)
    private String version;

    @Column(name = "MOVIE_NAME_ENG", nullable = false, unique = true)
    private String movieNameEng;

    @Column(name = "MOVIE_NAME_VIE", nullable = false, unique = true)
    private String movieNameVie;

    @Column(name = "Large_Image", nullable = false)
    private String largeImage;

    @Column(name = "SMALL_IMAGE", nullable = false)
    private String smallImage;
    * */

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

        assertDoesNotThrow(() -> movieDAO.insertMovie(movie));

    }
}