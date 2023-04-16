package org.hibernate.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serial;
import java.util.Set;

@Entity
@Table(name = "TYPE")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Type implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TYPE_ID", nullable = false)
    private Integer typeId;

    @Column(name = "TYPE_NAME", nullable = false, unique = true)
    private String typeName;

    @Column(name = "TYPE_DESCRIPTION", nullable = false)
    private String typeDescription;


    @OneToMany(mappedBy = "typeId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MovieType> movieTypes;
}

