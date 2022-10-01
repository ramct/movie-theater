package com.jpmc.theater.movietheaterapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Duration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jpmc.theater.movietheaterapi.entity.Movie;

class MovieTests {
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testHashCode() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", "Movie 1", Duration.ofMinutes(90), 12.5, 1);
		Movie turningRed = new Movie(2L, "Turning Red", "Movie 2", Duration.ofMinutes(85), 11, 0);

		assertNotEquals(spiderMan.hashCode(), turningRed.hashCode());

	}

	@Test
	void testGetTitle() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home","Movie 1", Duration.ofMinutes(90), 12.5, 1);
		assertEquals("Spider-Man: No Way Home", spiderMan.getTitle());
	}

	@Test
	void testGetRunningTime() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", "Movie 1", Duration.ofMinutes(90), 12.5, 1);
		assertEquals(Duration.ofMinutes(90), spiderMan.getRunningTime());
	}

	@Test
	void testGetTicketPrice() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", "Movie 1", Duration.ofMinutes(90), 12.5, 1);
		assertEquals(12.5, spiderMan.getTicketPrice());
	}

	@Test
	void testGetSpecialCode() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", "Movie 1", Duration.ofMinutes(90), 12.5, 1);
		assertEquals(1, spiderMan.getSpecialCode());
	}

	@Test
	void testEqualsObject() {
		Movie spiderMan = new Movie(1L, "Spider-Man: No Way Home", "Movie 1", Duration.ofMinutes(90), 12.5, 1);
		Movie turningRed = new Movie(2L, "Turning Red", "Movie 2", Duration.ofMinutes(85), 11, 0);

		assertEquals(true, spiderMan.equals(spiderMan));
		assertEquals(false, turningRed.equals(spiderMan));
	}

}
