package com.texoit.challenge.movies_reate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.texoit.challenge.movies_reate.repository")
//@EntityScan(basePackages = "com.texoit.challenge.movies_reate.data")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
