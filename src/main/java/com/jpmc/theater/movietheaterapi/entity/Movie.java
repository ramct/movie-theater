package com.jpmc.theater.movietheaterapi.entity;

import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {

	private final int movieId;
	
	private final String title;
	private final String description;
	private final Duration runningTime;
	private final double ticketPrice;
	private final int specialCode;

}