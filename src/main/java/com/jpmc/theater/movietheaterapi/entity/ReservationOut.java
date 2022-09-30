package com.jpmc.theater.movietheaterapi.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationOut {

	private String customerName;
	private int numberOfTickets;
	private double totalFee;

	private LocalDateTime showStartTime;
	private Movie movie;
}
