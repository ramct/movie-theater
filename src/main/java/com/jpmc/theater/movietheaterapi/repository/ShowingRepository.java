package com.jpmc.theater.movietheaterapi.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.movietheaterapi.entity.Movie;
import com.jpmc.theater.movietheaterapi.entity.Showing;

@Repository
public class ShowingRepository {

    @Autowired
    private JdbcTemplate template;
    
    @Autowired
    private MovieRepository movieRepos;

    public void createShowing(Movie movie, int sequenceOfTheDay, LocalDateTime startTime) {
        template.update("INSERT INTO movie (movie_id, sequence, start_date, start_time) VALUES (?,?,?,?)", 
                        movie.getMovieId(), sequenceOfTheDay, startTime.toLocalDate(), startTime.toLocalTime());
    }
    
    
    public List<Showing> allShows() {
    	List<Map<String, Object>> list =  template.queryForList("select id, sequence, start_date, start_time from showing");
        List<Showing> shows = new ArrayList<>();
        for (Map<String, Object> m : list) {
        	int movieId = (int) m.get("id");
        	int sequence = (int) m.get("sequence");
        	LocalDate localDate = (LocalDate) m.get("start_date");
        	LocalTime localTime = (LocalTime) m.get("start_time");
        	Movie movie = movieRepos.findMovieById(movieId);
        	Showing show = new Showing(movie, sequence, LocalDateTime.of(localDate, localTime));
        	shows.add(show);
        }
        return shows;
    }
    
}