package com.jpmc.theater.movietheaterapi.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jpmc.theater.movietheaterapi.entity.Customer;
import com.jpmc.theater.movietheaterapi.entity.Reservation;
import com.jpmc.theater.movietheaterapi.entity.ReservationOut;
import com.jpmc.theater.movietheaterapi.entity.Showing;
import com.jpmc.theater.movietheaterapi.repository.ShowingRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TheaterService {

	@Autowired
	ShowingRepository showingRepos;
	
	private List<Showing> schedule;
	
	@PostConstruct
	public void buildSchedule() {
		this.buildShowSchedule();
	}
	
	public List<Showing> getAllShows() {
		return showingRepos.allShows();
	}

	public void buildShowSchedule() {
		schedule = showingRepos.allShows();
	}

	public List<Showing> getSchedule() {
		return schedule;
	}
	
	
	public Showing getSchedule(int sequence) {
		Optional<Showing> optShowing = this.schedule.stream().filter(showing -> showing.getSequenceOfTheDay() == sequence).findFirst();
		if (optShowing.isPresent())
			return optShowing.get();
		return null;
	}

	public ReservationOut reserve(Customer customer, int showSequence, int numberOfTickets) {
		Showing showing = this.getSchedule(showSequence);
		Reservation r = new Reservation(customer, showing, numberOfTickets);
		ReservationOut out = new ReservationOut();
		out.setCustomerName(customer.getName());
		out.setMovie(showing.getMovie());
		out.setNumberOfTickets(numberOfTickets);
		out.setShowStartTime(showing.getShowStartTime());
		out.setTotalFee(r.totalFee());
		return out;
	}
	
	public String printScheduleAsString() {
		if (schedule == null) buildShowSchedule();
		StringBuilder scheduleString = new StringBuilder();
		scheduleString.append(String.format("Date %s%n", LocalDate.now()));
		scheduleString.append(
				String.format("==============================================================================%n"));
		schedule.forEach(s -> scheduleString
				.append(String.format("%d: %s %-30s %s $%05.02f%n", s.getSequenceOfTheDay(), s.getShowStartTime(),
						s.getMovie().getTitle(), humanReadableFormat(s.getMovie().getRunningTime()), s.getMovie().getTicketPrice())));
		scheduleString.append(
				String.format("===============================================================================%n"));
		return scheduleString.toString();
	}

	public String printScheduleAsJson() {
		if (schedule == null) buildShowSchedule();
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(new JavaTimeModule());
		String json = "";
		try {
			json = objMapper.writeValueAsString(schedule);
		} catch (JsonProcessingException e) {
			log.error("Failed to serialize object in JSON format. Json parse error", e);
		}
		return json;
	}

	private String humanReadableFormat(Duration duration) {
		long hour = duration.toHours();
		long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(hour);

		return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin,
				handlePlural(remainingMin));
	}

	// (s) postfix should be added to handle plural correctly
	private String handlePlural(long value) {
		if (value == 1) {
			return "";
		} else {
			return "s";
		}
	}

}
