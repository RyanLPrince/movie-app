package com.qa.business.service;

public interface IMovieService {
	String getAllMovies();	
	String getAMovie(Long id);
	String createMovie(String movieAsJSON);
	String deleteMovie(Long id);
	String updateMovie(String movieAsJSON);
}
