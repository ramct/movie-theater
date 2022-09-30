package com.jpmc.theater.movietheaterapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.jpmc.theater.movietheaterapi.config",
		"com.jpmc.theater.movietheaterapi.repository",
		"com.jpmc.theater.movietheaterapi.service",
		"com.jpmc.theater.movietheaterapi.controller"
	})
public class MovieTheaterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTheaterApiApplication.class, args);
	}

}
