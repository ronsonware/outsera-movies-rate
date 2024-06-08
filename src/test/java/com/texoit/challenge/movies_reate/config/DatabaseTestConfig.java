package com.texoit.challenge.movies_reate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Configuration
@Profile("test")
public class DatabaseTestConfig {

    @Bean
    public PostgreSQLContainer<?> postgresqlContainer() {
        PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("testdb")
                .withUsername("test")
                .withPassword("test");
        container.start();
        return container;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(PostgreSQLContainer<?> postgresqlContainer) {
        return DataSourceBuilder.create()
                .url(postgresqlContainer.getJdbcUrl())
                .username(postgresqlContainer.getUsername())
                .password(postgresqlContainer.getPassword())
                .driverClassName("org.postgresql.Driver")
                .build();
    }


}
