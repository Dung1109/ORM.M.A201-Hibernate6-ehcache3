package org.hibernate.movietypeDAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.entities.Movie;
import org.hibernate.entities.MovieType;
import org.hibernate.entities.Type;
import org.hibernate.query.Query;
import org.hibernate.utils.XmlConnectionConfig;

import java.util.List;

public class MovieTypeDAOImpl implements MovieTypeDAO {
    /*
    * public class MovieType {

    @Id
    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movieId;

    @Id
    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private Type typeId;

    @Column(name = "MT_DESCRIPTION", nullable = false)
    private String mtDescription;
    * */

    @Override
    public MovieType getMovieTypeById(Movie movie, Type type) {
        try (Session session = XmlConnectionConfig.getSession()) {
            // select * from MovieType where is composite key (movieId, typeId), using criteria query
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MovieType> cq = cb.createQuery(MovieType.class);
            Root<MovieType> root = cq.from(MovieType.class);
            cq.select(root);
            Predicate moviePredicate = cb.equal(root.get("movieId"), movie);
            Predicate typePredicate = cb.equal(root.get("typeId"), type);
            cq.where(cb.and(moviePredicate, typePredicate));
            Query<MovieType> query = session.createQuery(cq);

            return query.getSingleResult();

        }
    }

    @Override
    public List<MovieType> getAllMovieTypes() {
        try (Session session = XmlConnectionConfig.getSession()) {
            // select * from MovieType, using criteria query
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MovieType> cq = cb.createQuery(MovieType.class);
            Root<MovieType> root = cq.from(MovieType.class);
            cq.select(root);
            Query<MovieType> query = session.createQuery(cq);

            return query.getResultList();
        }
    }

    @Override
    public void updateMovieTypeById(Movie movie, Type type) {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();

            session.createMutationQuery("update MovieType mt set mt.mtDescription = :mtDescription " + "where mt.movieId = :movieId and mt.typeId = :typeId")
                    .setParameter("mtDescription", "new description")
                    .setParameter("movieId", movie)
                    .setParameter("typeId", type)
                    .executeUpdate();

            session.getTransaction().commit();
        }

    }

    @Override
    public void deleteMovieTypeById(Movie movie, Type type) {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            // delete from MovieType where is composite key (movieId, typeId), using hibernate 6
            session.createMutationQuery("delete from MovieType mt where mt.movieId = :movieId and mt.typeId = :typeId")
                    .setParameter("movieId", movie)
                    .setParameter("typeId", type)
                    .executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void insertMovieType(MovieType movieType) {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            session.persist(movieType);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteAllMovieTypes() {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            session.createQuery("delete from MovieType").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
