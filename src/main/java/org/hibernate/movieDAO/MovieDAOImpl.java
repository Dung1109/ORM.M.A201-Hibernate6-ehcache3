package org.hibernate.movieDAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.ConstraintViolation;
import org.hibernate.Session;
import org.hibernate.entities.Movie;
import org.hibernate.utils.ValidatorUtil;
import org.hibernate.utils.XmlConnectionConfig;

import java.util.List;
import java.util.Set;

public class MovieDAOImpl implements MovieDAO {
    @Override
    public Movie getMovieById(String id) {
        try (Session session = XmlConnectionConfig.getSession()) {
            return session.get(Movie.class, id);
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        try (Session session = XmlConnectionConfig.getSession()) {
            return session.createQuery("FROM Movie", Movie.class).list();
        }
    }

    @Override
    public void updateMovieById(String id, Movie movie) {
        // update using criteria query
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
            Predicate predicate = criteriaBuilder.equal(criteriaQuery.from(Movie.class).get("movieId"), id);
            criteriaQuery.where(predicate);
            Movie oldMovie = session.createQuery(criteriaQuery).getSingleResult();
            oldMovie.setMovieNameEng(movie.getMovieNameEng());
            oldMovie.setMovieNameVie(movie.getMovieNameVie());
            oldMovie.setActor(movie.getActor());
            oldMovie.setDirector(movie.getDirector());
            oldMovie.setDuration(movie.getDuration());
            oldMovie.setFromDate(movie.getFromDate());
            oldMovie.setToDate(movie.getToDate());
            oldMovie.setMovieProductionCompany(movie.getMovieProductionCompany());
            oldMovie.setVersion(movie.getVersion());
            oldMovie.setLargeImage(movie.getLargeImage());
            oldMovie.setSmallImage(movie.getSmallImage());
            oldMovie.setContent(movie.getContent());
            session.merge(oldMovie);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteMovieById(String id) {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            Movie movie = session.get(Movie.class, id);
            session.remove(movie);
            session.getTransaction().commit();
        }
    }

    @Override
    public void insertMovie(Movie movie) {
//
//        Set<ConstraintViolation<Movie>> violations = ValidatorUtil.validate(movie);
//        if (violations.size() > 0) {
//            for (ConstraintViolation<Movie> violation : violations) {
//                System.out.println(violation.getMessage());
//            }
//        } else {
//            try (Session session = XmlConnectionConfig.getSession()) {
//                session.beginTransaction();
//                session.persist(movie);
//                session.getTransaction().commit();
//            }
//        }

        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            // insert movie using HQL
            session.createQuery("INSERT INTO Movie (movieId,actor, content,director,duration,fromDate, toDate,movieProductionCompany,version,movieNameEng,movieNameVie,largeImage,smallImage) " +
                            "VALUES (:movieId, :actor, :content, :director, :duration, :fromDate, :toDate, :movieProductionCompany, :version, :movieNameEng, :movieNameVie, :largeImage, :smallImage)")
                    .setParameter("movieId", movie.getMovieId())
                    .setParameter("actor", movie.getActor())
                    .setParameter("content", movie.getContent())
                    .setParameter("director", movie.getDirector())
                    .setParameter("duration", movie.getDuration())
                    .setParameter("fromDate", movie.getFromDate())
                    .setParameter("toDate", movie.getToDate())
                    .setParameter("movieProductionCompany", movie.getMovieProductionCompany())
                    .setParameter("version", movie.getVersion())
                    .setParameter("movieNameEng", movie.getMovieNameEng())
                    .setParameter("movieNameVie", movie.getMovieNameVie())
                    .setParameter("largeImage", movie.getLargeImage())
                    .setParameter("smallImage", movie.getSmallImage())
                    .executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteAllMovies() {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            session.createQuery("DELETE Movie").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
