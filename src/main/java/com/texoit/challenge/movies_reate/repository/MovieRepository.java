package com.texoit.challenge.movies_reate.repository;

import com.texoit.challenge.movies_reate.data.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = """
        WITH split_producers AS (
            SELECT
                trim(unnest(regexp_split_to_array(producers, '\\s*,\\s*|\\s+and\\s+'))) as producer,
                movie_year
            FROM
                movies
            WHERE
                winner = true
        ),
        winner_years AS (
            SELECT
                trim(producer) as producer,
                movie_year,
                LAG(movie_year) OVER (PARTITION BY trim(producer) ORDER BY movie_year) AS previous_year
            FROM
                split_producers
        ),
        year_differences AS (
            SELECT
                producer,
                movie_year,
                previous_year,
                movie_year - previous_year AS year_difference
            FROM
                winner_years
            WHERE
                previous_year IS NOT NULL
        ),
        intervals AS (
            SELECT
                producer,
                MAX(year_difference) AS max_interval,
                MIN(year_difference) AS min_interval
            FROM
                year_differences
            GROUP BY
                producer
        ),
        global_max_min AS (
            SELECT
                MAX(max_interval) AS global_max,
                MIN(min_interval) AS global_min
            FROM
                intervals
        )
        SELECT
            difference.producer AS producer,
            difference.year_difference AS interval,
            difference.movie_year AS followingWin,
            difference.previous_year AS previousWin,
            CASE
                WHEN difference.year_difference = glob_max_min.global_max THEN 'max'
                WHEN difference.year_difference = glob_max_min.global_min THEN 'min'
                ELSE NULL
            END AS flag
        FROM
            year_differences difference,
            global_max_min glob_max_min
        WHERE
            difference.year_difference = glob_max_min.global_max
            OR difference.year_difference = glob_max_min.global_min
        ORDER BY
            flag, difference.producer;
    """, nativeQuery = true)
    List<Object[]> findCustomQuery();

    List<Movie> findByProducersLikeAndYear(String producer, Integer year);

}

