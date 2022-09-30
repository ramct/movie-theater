package com.jpmc.theater.movietheaterapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmc.theater.movietheaterapi.entity.Customer;
import com.jpmc.theater.movietheaterapi.entity.Reservation;
import com.jpmc.theater.movietheaterapi.entity.ReservationOut;
import com.jpmc.theater.movietheaterapi.entity.Showing;
import com.jpmc.theater.movietheaterapi.repository.ShowingRepository;

@Service
public class TheaterService {

	@Autowired
	ShowingRepository showingRepos;
	
	private List<Showing> schedule;
	
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
	
	
}
