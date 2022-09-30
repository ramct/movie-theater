package com.jpmc.theater.movietheaterapi.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Showing {
	private final Movie movie;
	private final int sequenceOfTheDay;
	private final LocalDateTime showStartTime;

	public boolean isSequence(int sequence) {
		return this.sequenceOfTheDay == sequence;
	}

}
