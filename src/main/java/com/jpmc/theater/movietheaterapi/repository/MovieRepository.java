package com.jpmc.theater.movietheaterapi.repository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.movietheaterapi.entity.Movie;

@Repository
public class MovieRepository {

    @Autowired
    private JdbcTemplate template;

    public void createMovie(String title,  String description, int runningTime, double ticketPrice, int specialCode) {
        template.update("INSERT INTO movie (title, release_year, rating) VALUES (?,?,?,?,?)", 
                        title, description, runningTime, ticketPrice, specialCode);
    }
    
    public List<Movie> allMovies() {
    	List<Map<String, Object>> list =  template.queryForList("id, title, description, running_time, ticket_price, special_code from movie");
        List<Movie> movies = new ArrayList<>();
        for (Map<String, Object> m : list) {
        	int id = (int) m.get("id");
        	String title = (String) m.get("title");
        	String description = (String) m.get("description");
        	int runningTime = (int) m.get("running_time");
        	Duration runningDuration = Duration.ofMinutes(runningTime);
        	double ticketPrice = (int) m.get("ticket_price");
        	int specialCode = (int) m.get("special_code");
        	Movie movie = new Movie(id, title, description, runningDuration, ticketPrice, specialCode);
        	movies.add(movie);
        }
        return movies;
    }
    
    public Movie findMovieById(int movieId) {
        List<Map<String, Object>> list =  template.queryForList("select title, description, running_time, ticket_price, special_code from movie where id = ?", movieId);
        List<Movie> movies = new ArrayList<>();
        for (Map<String, Object> m : list) {
        	int id = (int) m.get("id");
        	String title = (String) m.get("title");
        	String description = (String) m.get("description");
        	int runningTime = (int) m.get("running_time");
        	Duration runningDuration = Duration.ofMinutes(runningTime);
        	double ticketPrice = (int) m.get("ticket_price");
        	int specialCode = (int) m.get("special_code");
        	Movie movie = new Movie(id, title, description, runningDuration, ticketPrice, specialCode);
        	movies.add(movie);

        }
        return (!movies.isEmpty())?movies.get(0):null;
    }
}