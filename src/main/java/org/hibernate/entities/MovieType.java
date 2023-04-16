package org.hibernate.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "MOVIE_TYPE")
@Getter
@Setter
@ToString
public class MovieType {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieType movieType = (MovieType) o;
        return Objects.equals(movieId, movieType.movieId) && Objects.equals(typeId, movieType.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId);
    }
}
