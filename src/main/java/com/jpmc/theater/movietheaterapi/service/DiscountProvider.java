package com.jpmc.theater.movietheaterapi.service;

import com.jpmc.theater.movietheaterapi.entity.Movie;
import com.jpmc.theater.movietheaterapi.entity.Showing;

public class DiscountProvider {

	private static final int MOVIE_CODE_SPECIAL = 1;

	private Showing showing;

	public DiscountProvider(Showing showing) {
		this.showing = showing;
	}

	public double getDiscount() {
		Movie movie = showing.getMovie();

		// Special Discount
		double specialDiscount = 0;
		if (MOVIE_CODE_SPECIAL == movie.getSpecialCode()) {
			specialDiscount = movie.getTicketPrice() * 0.2; // 20% discount for special movie
		}

		// Time discount
		// 25% discount between 11am and 4 pm
		int showStartHour = showing.getShowStartTime().getHour();
		if (showStartHour >= 11 && showStartHour <= 16) {
			specialDiscount = movie.getTicketPrice() * 0.25;
		}

		// Show sequence discount
		int showSequence = showing.getSequenceOfTheDay();
		double sequenceDiscount;
		switch (showSequence) {
		case 1:
			sequenceDiscount = 3;
			break;
		case 2:
			sequenceDiscount = 2;
			break;
		case 7:
			sequenceDiscount = 1;
			break;
		default:
			sequenceDiscount = 0;
		}

		// biggest discount wins
		return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
	}

}
