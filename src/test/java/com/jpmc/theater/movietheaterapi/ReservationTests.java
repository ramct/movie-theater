package com.jpmc.theater.movietheaterapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.movietheaterapi.entity.Customer;
import com.jpmc.theater.movietheaterapi.entity.Movie;
import com.jpmc.theater.movietheaterapi.entity.Reservation;
import com.jpmc.theater.movietheaterapi.entity.Showing;

class ReservationTests {

	@Test
	void totalFee() {
		var customer = new Customer("John Doe");
		var showing = new Showing(new Movie(1L, "Spider-Man: No Way Home", "Movie 1", Duration.ofMinutes(90), 12.5, 1), 1,
				LocalDateTime.now());
		double result = new Reservation(customer, showing, 3).totalFee();
		assertEquals(28.5, result);
	}
}
