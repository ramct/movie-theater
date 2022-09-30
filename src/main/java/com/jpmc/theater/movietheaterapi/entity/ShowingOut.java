package com.jpmc.theater.movietheaterapi.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowingOut {

	private List<Showing> shows;
}
