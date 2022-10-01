package com.jpmc.theater.movietheaterapi.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.theater.movietheaterapi.entity.Customer;
import com.jpmc.theater.movietheaterapi.entity.ReservationInput;
import com.jpmc.theater.movietheaterapi.entity.ReservationOut;
import com.jpmc.theater.movietheaterapi.entity.ShowingOut;
import com.jpmc.theater.movietheaterapi.service.TheaterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/theater")
@Slf4j
public class TheaterController {
	
	@Autowired
	TheaterService theaterService;

	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/shows")
	public ShowingOut listShowings() {
		return new ShowingOut(theaterService.getAllShows());
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,  value="/reserve")
	public ReservationOut reserve(@RequestBody ReservationInput input) {
		log.info("reserve: Request to reserve show with input {}", input);
		ReservationOut reservation = null;
		try {
			reservation = theaterService.reserve(new Customer(input.getCustomerName()), input.getShowSequence(), input.getNumberOfTickets());
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw new IllegalStateException("not able to find any showing for given sequence " + input.getShowSequence());
		}
		if (reservation != null)
			return reservation;

		throw new IllegalStateException("Reservation not available at this time " + input.getShowSequence());
	}

	
}
