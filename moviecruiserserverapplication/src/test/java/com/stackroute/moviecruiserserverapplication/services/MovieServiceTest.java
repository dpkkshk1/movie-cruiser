package com.stackroute.moviecruiserserverapplication.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;
import com.stackroute.moviecruiserserverapplication.service.MovieServiceImpl;

public class MovieServiceTest {

	/**
	 * Mocking Movie Repository object
	 */
	@Mock
	private transient MovieRepository movieRepository;
	/**
	 * mocking Movie Domain Class Object
	 */
	@Mock
	private transient Movie movie;
	/**
	 * Injecting Moked objects
	 */
	@InjectMocks
	private transient MovieServiceImpl movieServiceImpl;

	transient Optional<Movie> options;

	/**
	 * this method will run before each test
	 */
	@Before
	public void setUpMock() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1,"123", "endgame", "nyc", "ww.abc.com", "28/04/2019", 121.2, 123,"user123");
		options = Optional.of(movie);
	}

	/**
	 * test to check mock creation
	 */
	@Test
	public void testMockCreation() {
		assertNotNull(movie);
		assertNotNull("JPA creation Failed use @injectMocks on movieImpl", movie);
	}

	/**
	 * Test for saveMovie for success
	 * 
	 * @throws MovieAlreadyExistsException
	 */
	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException {
		when(movieRepository.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
		assertTrue("Save Movie Failed call to movieimpl is false,check this", flag);
		verify(movieRepository, times(1)).save(movie);
	}

	/**
	 * Test for saveMovie for failure
	 * 
	 * @throws MovieAlreadyExistsException
	 */
	@Test(expected = MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException {
		when(movieRepository.findById(1)).thenReturn(options);
		when(movieRepository.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
		assertFalse("test Case failed", flag);
		verify(movieRepository, times(1)).findById(movie.getId());
	}

	/**
	 * test for update movie
	 * 
	 * @throws MovieNotFoundException
	 */
	@Test
	public void testUpdateMovie() throws MovieNotFoundException {
		when(movieRepository.findById(1)).thenReturn(options);
		when(movieRepository.save(movie)).thenReturn(movie);
		movie.setComments("not good");
		final Movie movie1 = movieServiceImpl.updateMovie(movie);
		assertEquals("updating movie failed", "not good", movie1.getComments());
		verify(movieRepository, times(1)).save(movie);
		verify(movieRepository, times(1)).findById(movie.getId());
	}

	/**
	 * test for delete movie
	 * 
	 * @throws MovieNotFoundException
	 */
	@Test
	public void testDeleteMovie() throws MovieNotFoundException {
		when(movieRepository.findById(1)).thenReturn(options);
		doNothing().when(movieRepository).delete(movie);
		final boolean flag = movieServiceImpl.deleteMovieById(1);
		assertEquals("Delete Movie Failed",true, flag);
		verify(movieRepository, times(1)).delete(movie);
		verify(movieRepository, times(1)).findById(movie.getId());
	}

	/**
	 * test for getmoviebyid
	 * 
	 * @throws MovieNotFoundException
	 */
	@Test
	public void testGetMovieById() throws MovieNotFoundException {
		when(movieRepository.findById(1)).thenReturn(options);
		final Movie moviefetched = movieServiceImpl.getMovieById(1);
		assertEquals("fetching movie by id Failed", moviefetched, movie);
		verify(movieRepository, times(1)).findById(movie.getId());
	}

	/**
	 * test for getAllMovies
	 */
	@Test
	public void testGetMyMovies() {
		final List<Movie> movieList = new ArrayList<>(1);
		movieList.add(movie);
		when(movieRepository.findByUserId("user123")).thenReturn(movieList);
		final List<Movie> newMovieList = movieServiceImpl.getMyMovies("user123");
		assertEquals(movieList, newMovieList);
		verify(movieRepository, times(1)).findByUserId("user123");
	}

}
