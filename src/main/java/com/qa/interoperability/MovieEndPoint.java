package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.IMovieService;

@Path("/movie")
public class MovieEndPoint {
	
	@Inject
	private IMovieService service;
	
	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getAllMovies() {
		return service.getAllMovies();
	}
	@GET
	@Path("/json/{id}")//id is dynamically generated so we do not specify a specific id value here
	@Produces({ "application/json" })
	public String getAMovie(@PathParam("id")Long id) {//reference to id method
		return service.getAMovie(id);
	}
	
	@POST
	@Path("/json/")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String createMovie(String movieAsJSON) {
		return service.createMovie(movieAsJSON);
	}
	
}