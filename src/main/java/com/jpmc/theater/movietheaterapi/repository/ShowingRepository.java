package com.jpmc.theater.movietheaterapi.repository;

import java.sql.Date;
import java.sql.Time;
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

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
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
        	log.info("allShows: Showing {}", m.values());
        	long movieId = (long) m.get("id");
        	int sequence = (int) m.get("sequence");
        	LocalDate localDate = ((Date) m.get("start_date")).toLocalDate(); 
        	LocalTime localTime = ((Time) m.get("start_time")).toLocalTime();
        	Movie movie = movieRepos.findMovieById(movieId);
        	Showing show = new Showing(movie, sequence, LocalDateTime.of(localDate, localTime));
        	shows.add(show);
        }
        return shows;
    }
    
}