package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qa.business.service.IMovieService;

@Path("/Movie")
public class MovieEndPoint {
	
	@Inject
	private IMovieService service;
	
	@GET
	@Path("/json")
	@Produces({ "application/jason" })
	public String getAllMovies() {
		return service.getAllMovies();
	}
}