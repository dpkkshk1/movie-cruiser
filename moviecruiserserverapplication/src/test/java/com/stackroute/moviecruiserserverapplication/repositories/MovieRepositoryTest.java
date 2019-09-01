package com.stackroute.moviecruiserserverapplication.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepositoryTest {
	/**
	 * movie repo object
	 */
	@Autowired
	private MovieRepository movieRepository;

	/**
	 * test for saveMovie Method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSaveMovie() throws Exception {
		Movie mov =movieRepository.save(new Movie(0,"1234", "endgame", "nyc", "ww.abc.com", "28/04/2019", 121.2, 123,"user123"));
		final Movie movie = movieRepository.findById(mov.getId()).get();
		assertThat(movie.getId()).isEqualTo(mov.getId());
	}

	/**
	 * test for updateMovie Method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateMovie() throws Exception {
		Movie mov = movieRepository.save(new Movie(0,"1234", "endgame", "nyc", "ww.abc.com", "28/04/2019", 121.2, 123,"user123"));
		final Movie movie = movieRepository.findById(mov.getId()).get();
		assertThat(movie.getTitle()).isEqualTo("endgame");
		movie.setComments("nyc movie");
		movieRepository.save(movie);
		final Movie testMovie = movieRepository.getOne(movie.getId());
		assertThat(testMovie.getComments()).isEqualTo("nyc movie");

	}

	/**
	 * test for DeleteMovie Method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteMovie() throws Exception {
		Movie mov = movieRepository.save(new Movie(0,"1234", "endgame", "nyc", "ww.abc.com", "28/04/2019", 121.2, 123,"user123"));
		final Movie movie = movieRepository.findById(mov.getId()).get();
		assertThat(movie.getTitle()).isEqualTo("endgame");
		movieRepository.delete(movie);
		assertEquals(Optional.empty(), movieRepository.findById(mov.getId()));
	}

	/**
	 * test for GetMovieById Method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetMovie() throws Exception {
		Movie mov = movieRepository.save(new Movie(0,"1234", "endgame", "nyc", "ww.abc.com", "28/04/2019", 121.2, 123,"user123"));
		final Movie movie = movieRepository.findById(mov.getId()).get();
		assertEquals(movie.getTitle(), "endgame");
	}

	/**
	 * test for GetAllMovie Method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllMovie() throws Exception {
		movieRepository.save(new Movie(0,"1234", "endgame1", "nyc", "ww.abc.com", "28/04/2019", 121.2, 123,"user123"));
		movieRepository.save(new Movie(0,"1235", "endgame2", "nyc", "ww.abc.com", "28/04/2019", 121.2, 123,"user123"));
		movieRepository.save(new Movie(0,"1236", "endgame3", "nyc", "ww.abc.com", "28/04/2019", 121.2, 123,"user123"));
		final List<Movie> movieList = movieRepository.findByUserId("user123");
		assertEquals(movieList.get(2).getTitle(), "endgame3");
	}
	@After
	public void tearDown(){
		movieRepository.deleteAllInBatch();
	}

}
