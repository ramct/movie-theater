package com.jpmc.theater.movietheaterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
