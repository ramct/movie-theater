package com.jpmc.theater.movietheaterapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpmc.theater.movietheaterapi.entity.Customer;
import com.jpmc.theater.movietheaterapi.entity.ReservationOut;
import com.jpmc.theater.movietheaterapi.service.TheaterService;

@SpringBootTest
class TheaterServiceTests {
	
	@Autowired
	TheaterService service;
	
	@Test
	void totalFeeForCustomer() {
		Customer john = new Customer("John Doe");
		ReservationOut reservation = service.reserve(john, 2, 4);
		assertEquals(37.5, reservation.getTotalFee());
	}

	@Test
	void printMovieSchedule() {
		assertTrue(!service.printScheduleAsString().isEmpty());
		assertTrue(!service.printScheduleAsJson().isEmpty());
	}
}
