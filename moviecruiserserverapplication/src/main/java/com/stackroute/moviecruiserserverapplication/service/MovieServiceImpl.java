package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	private final transient MovieRepository movieRepository;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}

	/**
	 * method impl to save movie
	 */
	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistsException {
		final Optional<Movie> movieObject = movieRepository.findById(movie.getId());
		if (movieObject.isPresent()) {
			throw new MovieAlreadyExistsException("Could not save movie, movie already exist");
		}
		movieRepository.save(movie);
		return true;
	}

	/**
	 * method impl to update movie
	 */
	@Override
	public Movie updateMovie(final Movie movieUpdate) throws MovieNotFoundException {
		final Movie movie = movieRepository.findById(movieUpdate.getId()).orElse(null);
		if (movie == null) {
			throw new MovieNotFoundException("Could not update movie, Movie Not Found");
		}
		movie.setComments(movieUpdate.getComments());
		movieRepository.save(movie);
		return movie;
	}

	/**
	 * method impl to delete a movie by id
	 */
	@Override
	public boolean deleteMovieById(int Id) throws MovieNotFoundException {
		final Movie movie = movieRepository.findById(Id).orElse(null);
		if (movie == null) {
			throw new MovieNotFoundException("Could not delete movie, Movie not found");
		}
		movieRepository.delete(movie);
		return true;
	}

	/**
	 * method impl to get movie details by id
	 */
	@Override
	public Movie getMovieById(int Id) throws MovieNotFoundException {
		final Movie movie = movieRepository.findById(Id).get();
		if (movie == null) {
			throw new MovieNotFoundException("Movie not found");
		}
		return movie;
	}

	/**
	 * Method impl to get List of all movies
	 */
	@Override
	public List<Movie> getMyMovies(String userId) {
		final List<Movie> movieList = movieRepository.findByUserId(userId);
		return movieList;
	}

}
