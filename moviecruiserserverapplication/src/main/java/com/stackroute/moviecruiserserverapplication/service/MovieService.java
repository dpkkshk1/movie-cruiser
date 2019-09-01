package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;

public interface MovieService {
	/**
	 * method declaration for saving a movie
	 * 
	 * @param movie
	 * @return
	 * @throws MovieAlreadyExistsException
	 */
	boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;

	/**
	 * method declaration for updating existing movie
	 * 
	 * @param movie
	 * @return
	 * @throws MovieNotFoundException
	 */
	Movie updateMovie(Movie movie) throws MovieNotFoundException;

	/**
	 * method declaration for deleting a movie
	 * 
	 * @param Id
	 * @return
	 * @throws MovieNotFoundException
	 */
	boolean deleteMovieById(int Id) throws MovieNotFoundException;

	/**
	 * method declaration for getting movie by id
	 * 
	 * @param Id
	 * @return
	 * @throws MovieNotFoundException
	 */
	Movie getMovieById(int Id) throws MovieNotFoundException;

	/**
	 * method declaration for getting list of all movies for specific user
	 * 
	 * @return List<Movie>
	 */

	List<Movie> getMyMovies(String userId);

}
