package com.jpmc.theater.movietheaterapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmc.theater.movietheaterapi.entity.Showing;
import com.jpmc.theater.movietheaterapi.repository.ShowingRepository;

@Service
public class TheaterService {

	@Autowired
	ShowingRepository showingRepos;
	
	public List<Showing> getAllShows() {
		return showingRepos.allShows();
	}
	
}
