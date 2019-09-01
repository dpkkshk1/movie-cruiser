package com.stackroute.moviecruiserserverapplication.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/movieservice")
public class MovieController {

	private MovieService movieService;

	/**
	 * Constructor injection to obtain movieService bean
	 * 
	 * @param movieService
	 */
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	/**
	 * method to save a movie
	 * 
	 * @param movie
	 * @return movie/failure message
	 */
	@PostMapping("/movie")
	ResponseEntity<?> saveMovie(@RequestBody final Movie movie,HttpServletRequest request,HttpServletResponse response) {
		String authHeader = request.getHeader("authorization");
		final String token = authHeader.split(" ")[1];
		final String userId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
		try {
			movie.setMovieId(Integer.toString(movie.getId()));
			movie.setUserId(userId);
			movieService.saveMovie(movie);
			return  new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
	}

	/**
	 * method to update movie
	 * 
	 * @param id
	 * @param movie
	 * @return movie
	 */
	@PutMapping(path = "/movie/{Id}")
	public ResponseEntity<?> updateMovie(@PathVariable final int Id, @RequestBody final Movie movie) {
		ResponseEntity<?> responseEntity;
		try {
			final Movie movieFetched = movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movieFetched, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	/**
	 * method to delete movie by id
	 * 
	 * @param Id
	 * @return String
	 */
	@DeleteMapping(path = "/movie/{Id}")
	public ResponseEntity<?> deleteMovie(@PathVariable final int Id) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieById(Id);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);

		}
		responseEntity = new ResponseEntity<String>("Movie Deleted Successfully", HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * method to retrieve movie by id
	 * 
	 * @param Id
	 * @return movie
	 */

	@GetMapping(path = "/movie/{Id}")
	public ResponseEntity<?> getMovieById(@PathVariable final int Id) {
		ResponseEntity<?> responseEntity;
		Movie fetchedMovie = null;
		try {
			fetchedMovie = movieService.getMovieById(Id);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<Movie>(fetchedMovie, HttpStatus.OK);
		System.out.println("resss======>" + responseEntity);
		return responseEntity;
	}

	/**
	 * method to fetch all movies
	 * 
	 * @return List<Movies>
	 */

	@GetMapping("/movies")
	public @ResponseBody ResponseEntity<List<Movie>> getMyMovies(final HttpServletRequest request) {
		String authHeader = request.getHeader("authorization");
		final String token = authHeader.split(" ")[1];
		final String userId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
		System.out.println(userId);
		return new ResponseEntity<List<Movie>>(movieService.getMyMovies(userId), HttpStatus.OK);
	}

}
