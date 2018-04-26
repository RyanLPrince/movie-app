package com.qa.business.repository;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class MovieDBRepository implements IMovieRepository {

	private static final Logger LOGGER = Logger.getLogger(MovieDBRepository.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	@Override
	public String getAllMovies() {
		LOGGER.info("MovieDBRepository getAllMovies");
		Query query = manager.createQuery("Select m FROM Movie m");
		Collection<Movie> movies = (Collection<Movie>) query.getResultList();
		return util.getJSONForObject(movies);
	}
	
	@Override
	public String getAMovie(Long id) {
		Movie aMovie=findMovie(id);
		if (aMovie!=null) {
			return util.getJSONForObject(aMovie);
		}
		else {
			return "{\"message\":\"movie not fount\"}";
		}
	}

	private Movie findMovie(Long id) {
		return manager.find(Movie.class,id);
	}
	
	@Transactional(REQUIRED)
	@Override
	public String createMovie(String movieAsJSON) {
		Movie movie =util.getObjectForJSON(movieAsJSON, Movie.class);
		manager.persist(movie);
		//return util.getJSONForObject(movie.getId());
		return "{\"message\":\"movie has been created\"}";
	}
	
	@Transactional(REQUIRED)
	@Override
	public String deleteMovie(Long id) {
		Movie movie=findMovie(id);
		if (movie!=null) {
			manager.remove(movie);
			return "{\"message\":\"movie has been deleted\"}";
		}
		else {
			return "{\"message\":\"movie does not exist\"}";
		}
	}

	@Transactional(REQUIRED)
	@Override
	public String updateMovie(String movieAsJSON) {
		Movie updatedMovie=util.getObjectForJSON(movieAsJSON, Movie.class);
		Movie oldMovie =findMovie(updatedMovie.getId());
		if (oldMovie!=null) {
			manager.merge(updatedMovie);
			return  "{\"message\":\"movie has been updated\"}";
		}
		else {
			return "{\"message\":\"movie does not exist\"}";
		}
		
	}
}
