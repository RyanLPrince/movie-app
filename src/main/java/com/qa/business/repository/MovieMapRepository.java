package com.qa.business.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

public class MovieMapRepository implements IMovieRepository {
	
	Map<Long, Movie> movieMap;
	
	@Inject
	private JSONUtil util;
	
	public MovieMapRepository() {
		movieMap=new HashMap<Long, Movie>();
	}

	@Override
	public String getAllMovies() {
		return util.getJSONForObject(movieMap);
	}

	@Override
	public String getAMovie(Long id) {
		return util.getJSONForObject(movieMap.get(id));
	}

	@Override
	public String createMovie(String movieAsJSON) {
		Movie movie=util.getObjectForJSON(movieAsJSON, Movie.class);
		movieMap.put(movie.getId(),movie);
		return "{\"message\":\"movie has been created\"}";
	}

	@Override
	public String deleteMovie(Long id) {
		movieMap.remove(id);
		return "{\"message\":\"movie has been deleted\"}";
	}

	@Override
	public String updateMovie(String movieAsJSON) {
		Movie movie=util.getObjectForJSON(movieAsJSON, Movie.class);
		if (!movieMap.containsKey(movie.getId())) {
			movieMap.put(movie.getId(), movie);
			return "{\"message\":\"movie has been updated\"}";;
		}else {
			return "{\"message\":\"movie has not been updated\"}";;
		}
		
	}	
}
