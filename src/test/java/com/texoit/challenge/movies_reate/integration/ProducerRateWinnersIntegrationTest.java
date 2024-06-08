package com.texoit.challenge.movies_reate.integration;

import com.texoit.challenge.movies_reate.data.model.Movie;
import com.texoit.challenge.movies_reate.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProducerRateWinnersIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    private static String ENDPOINT = "/producers/rate-winners";

    @Test
    void whenRateWinnersShouldReturnMinAndMaxSuccessfully() throws Exception {
        // Act
        ResultActions perform = mockMvc.perform(get(ENDPOINT));

        // Assert
        perform.andExpect(status().isOk())
            .andExpect(content().json("""
                {
                  "min": [
                    {
                      "producer": "Joel Silver",
                      "interval": 1,
                      "followingWin": 1991,
                      "previousWin": 1990
                    }
                  ],
                  "max": [
                    {
                      "producer": "Matthew Vaughn",
                      "interval": 13,
                      "followingWin": 2015,
                      "previousWin": 2002
                    }
                  ]
                }
            """));
    }

    @Test
    void whenUpdatedMinMoviesAndSearchedRateWinnersShouldReturnMinAndMaxSuccessfully() throws Exception {
        // Arrange
        Movie boDerek = movieRepository.findByProducersLikeAndYear("Bo Derek", 1990).stream().findFirst().get();
        boDerek.setYear(1984);
        movieRepository.save(boDerek);

        // Act
        ResultActions perform = mockMvc.perform(get(ENDPOINT));

        // Assert
        perform.andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                      "min": [
                        {
                          "producer": "Bo Derek",
                          "interval": 0,
                          "followingWin": 1984,
                          "previousWin": 1984
                        }
                      ],
                      "max": [
                        {
                          "producer": "Matthew Vaughn",
                          "interval": 13,
                          "followingWin": 2015,
                          "previousWin": 2002
                        }
                      ]
                    }
                """));

        resetMovie(boDerek);
    }

    @Test
    void whenUpdatedOtherMinMoviesAndSearchedRateWinnersShouldReturnMinAndMaxSuccessfully() throws Exception {
        // Arrange
        Movie boDerek = movieRepository.findByProducersLikeAndYear("Bo Derek", 1990).stream().findFirst().get();
        boDerek.setYear(1985);
        movieRepository.save(boDerek);

        // Act
        ResultActions perform = mockMvc.perform(get(ENDPOINT));

        // Assert
        perform.andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                      "min": [
                        {
                          "producer": "Bo Derek",
                          "interval": 1,
                          "followingWin": 1985,
                          "previousWin": 1984
                        },
                        {
                          "producer": "Joel Silver",
                          "interval": 1,
                          "followingWin": 1991,
                          "previousWin": 1990
                        }
                      ],
                      "max": [
                        {
                          "producer": "Matthew Vaughn",
                          "interval": 13,
                          "followingWin": 2015,
                          "previousWin": 2002
                        }
                      ]
                    }
                """));

        resetMovie(boDerek);
    }

    @Test
    void whenUpdatedMaxMoviesAndSearchedRateWinnersShouldReturnMinAndMaxSuccessfully() throws Exception {
        // Arrange
        Movie boDerek = movieRepository.findByProducersLikeAndYear("Bo Derek", 1990).stream().findFirst().get();
        boDerek.setYear(2023);
        movieRepository.save(boDerek);

        // Act
        ResultActions perform = mockMvc.perform(get(ENDPOINT));

        // Assert
        perform.andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                      "min": [
                        {
                          "producer": "Joel Silver",
                          "interval": 1,
                          "followingWin": 1991,
                          "previousWin": 1990
                        }
                      ],
                      "max": [
                        {
                          "producer": "Bo Derek",
                          "interval": 39,
                          "followingWin": 2023,
                          "previousWin": 1984
                        }
                      ]
                    }
                """));

        resetMovie(boDerek);
    }

    @Test
    void whenUpdatedOtherMaxMoviesAndSearchedRateWinnersShouldReturnMinAndMaxSuccessfully() throws Exception {
        // Arrange
        Movie boDerek = movieRepository.findByProducersLikeAndYear("Bo Derek", 1990).stream().findFirst().get();
        boDerek.setYear(1997);
        movieRepository.save(boDerek);

        // Act
        ResultActions perform = mockMvc.perform(get(ENDPOINT));

        // Assert
        perform.andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                       "min": [
                         {
                           "producer": "Joel Silver",
                           "interval": 1,
                           "followingWin": 1991,
                           "previousWin": 1990
                         }
                       ],
                       "max": [
                         {
                           "producer": "Bo Derek",
                           "interval": 13,
                           "followingWin": 1997,
                           "previousWin": 1984
                         },
                         {
                           "producer": "Matthew Vaughn",
                           "interval": 13,
                           "followingWin": 2015,
                           "previousWin": 2002
                         }
                       ]
                     }
                """));

        resetMovie(boDerek);
    }

    private void resetMovie(Movie movie) {
        movie.setYear(1990);
        movieRepository.save(movie);
    }
}
