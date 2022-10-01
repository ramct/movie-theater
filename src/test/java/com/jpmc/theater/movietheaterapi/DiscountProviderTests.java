package com.jpmc.theater.movietheaterapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jpmc.theater.movietheaterapi.entity.Customer;
import com.jpmc.theater.movietheaterapi.entity.Movie;
import com.jpmc.theater.movietheaterapi.entity.Reservation;
import com.jpmc.theater.movietheaterapi.entity.Showing;

class DiscountProviderTests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void specialMovieWith20PercentDiscount() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", " Movie 1", Duration.ofMinutes(90), 12.5, 1);
		Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		Reservation reservation = new Reservation(new Customer("John"), showing, 4);
		assertEquals(10.0, reservation.calculateTicketPrice());
	}

	@Test
	void firstShowOfDayDiscountGives$3() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", " Movie 1", Duration.ofMinutes(90), 12.5, 2);
		Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		Reservation reservation = new Reservation(new Customer("John"), showing, 1);
		assertEquals(9.5, reservation.calculateTicketPrice());
	}

	@Test
	void secondShowOfDayDiscountGives$2() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", " Movie 1",  Duration.ofMinutes(90), 12.5, 2);
		Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		Reservation reservation = new Reservation(new Customer("John"), showing, 1);
		assertEquals(10.5, reservation.calculateTicketPrice());
	}

	@Test
	void seventhShowOfDayDiscountGives$1() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", " Movie 1",  Duration.ofMinutes(90), 12.5, 2);
		Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		Reservation reservation = new Reservation(new Customer("John"), showing, 1);
		assertEquals(11.5, reservation.calculateTicketPrice());
	}

	@Test
	void givesMaxDiscountForSpecialMovieAndFirstShowOfDay() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", " Movie 1",  Duration.ofMinutes(90), 12.5, 1);
		Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		Reservation reservation = new Reservation(new Customer("John"), showing, 1);

		// FirstShowOfDay discount wins
		assertEquals(9.5, reservation.calculateTicketPrice());
	}

	@Test
	void givesMaxDiscountForSpecialMovieAndSecondShowOfDay() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", " Movie 1",  Duration.ofMinutes(90), 12.5, 1);
		Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		Reservation reservation = new Reservation(new Customer("John"), showing, 1);

		// SpecialMovie discount wins
		assertEquals(10.0, reservation.calculateTicketPrice());
	}

	@Test
	void givesMaxDiscountForSpecialMovieAndSeventhShowOfDay() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", " Movie 1",  Duration.ofMinutes(90), 12.5, 1);
		Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		Reservation reservation = new Reservation(new Customer("John"), showing, 1);

		// SpecialMovie discount wins
		assertEquals(10.0, reservation.calculateTicketPrice());
	}

	@Test
	void gives25PercentDiscountWhenStartTimeBetween11And4pm() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", " Movie 1",  Duration.ofMinutes(90), 12.5, 2);
		Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
		Reservation reservation = new Reservation(new Customer("John"), showing, 1);

		assertEquals(9.375, reservation.calculateTicketPrice());
	}

}
