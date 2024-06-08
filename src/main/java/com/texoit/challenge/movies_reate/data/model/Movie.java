package com.texoit.challenge.movies_reate.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "movies")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_year")
    private Integer year;

    private String title;
    private String studios;

    private String producers;

    private Boolean winner;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "movie_producer",
//            joinColumns = @JoinColumn(name = "movie_id"),
//            inverseJoinColumns = @JoinColumn(name = "producer_id")
//    )
//    private Set<Producer> producers = new HashSet<>();

}
