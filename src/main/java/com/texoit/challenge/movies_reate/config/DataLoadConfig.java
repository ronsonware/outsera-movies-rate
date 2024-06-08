package com.texoit.challenge.movies_reate.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class DataLoadConfig implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DataLoadConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Path csvPath = Path.of("src/main/resources/data/movielist (2).csv");

        try (Stream<String> lines = Files.lines(csvPath)) {
            lines.skip(1)
                    .map(line -> line.split(";"))
                    .forEach(data -> {
                        jdbcTemplate.update("INSERT INTO movies (movie_year, title, studios, producers, winner) VALUES (?, ?, ?, ?, ?)",
                                Integer.parseInt(data[0]), data[1], data[2], data[3], data.length == 5 && "yes".equalsIgnoreCase(data[4]));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
