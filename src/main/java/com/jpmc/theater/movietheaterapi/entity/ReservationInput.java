package com.jpmc.theater.movietheaterapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationInput {
	
	private final String customerName;
	private final int numberOfTickets;
	private final int showSequence;

}
