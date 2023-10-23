package com.scores.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.scores")
@EntityScan("com.scores.infrastructure.repository.entity")
public class DogoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogoodApplication.class, args);
	}

}
