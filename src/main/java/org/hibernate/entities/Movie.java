package org.hibernate.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "MOVIE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
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
//    @FutureDate
    private LocalDate fromDate;

    @Column(name = "TO_DATE", nullable = false)
//    @FutureDate
    private LocalDate toDate;

    @Column(name = "MOVIE_PRODUCTION_COMPANY", nullable = false)
    private String movieProductionCompany;

    @Column(name = "VERSION", nullable = false)
    private String version;

    @Column(name = "MOVIE_NAME_ENG", nullable = false, unique = true)
    private String movieNameEng;

    @Column(name = "MOVIE_NAME_VIE", nullable = false, unique = true)
    @Nationalized
    private String movieNameVie;

    @Column(name = "Large_Image", nullable = false)
    private String largeImage;

    @Column(name = "SMALL_IMAGE", nullable = false)
    private String smallImage;

    @OneToMany(mappedBy = "movieId", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<MovieType> movieTypes;

}
