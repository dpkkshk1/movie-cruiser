package com.stackroute.moviecruiserserverapplication.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserserverapplication.controller.MovieController;
import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieControllerTest.class)
public class MovieControllerTest {

	/**
	 * create mvc object reference
	 */
	@Autowired
	private transient MockMvc mvc;
	/**
	 * reference to movie service object
	 */
	@MockBean
	private transient MovieService movieService;
	/**
	 * Mock for controller
	 */
	@InjectMocks
	private transient MovieController movieController;
	/**
	 * reference to movie object
	 */
	private transient Movie movie;
	/**
	 * create list of movie
	 */
	private transient List<Movie> movieList;

	/**
	 * setting up requirement
	 */

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie(3,"1235", "endgame", "nyc", "ww.abc.com", "28/04/2019", 121.2, 123,"user123");
		mvc = MockMvcBuilders.standaloneSetup(movieController).build();
		movieList = new ArrayList<>();
		movie = new Movie(1,"123", "endgame", "nyc", "ww.abc.com", "28/04/2019", 121.2, 123,"user123");
		movieList.add(movie);
		movie = new Movie(2,"1234","infinity war", "nyc", "ww.abc.com", "28/04/2019", 121.2, 123,"user123");
		movieList.add(movie);
	}

	/**
	 * method for test of saving new movie
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSaveNewMovieSuccess() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwiaWF0IjoxNTUzMzQyNDk2fQ.NcjymPvlji7OutsksJUKPsFonmEaHybk2AUZYAcPwHg";
		when(movieService.saveMovie(movie)).thenReturn(true);
		mvc.perform(post("/api/v1/movieservice/movie").header("authorization", "Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie)))
				.andExpect(status().isCreated()).andDo(print());
		verify(movieService, times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}

	/**
	 * test for test of update movie method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateMovieSuccess() throws Exception {
		movie.setComments("not good");
		when(movieService.updateMovie(movie)).thenReturn(movieList.get(0));
		mvc.perform(put("/api/v1/movieservice/movie/{Id}", 1).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie)))
				.andExpect(status().isOk());
		verify(movieService, times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}

	/**
	 * test for delete movie method
	 * 
	 * @throws Exception
	 */

	@Test
	public void testDeleteById() throws Exception {
		when(movieService.deleteMovieById(1)).thenReturn(true);
		mvc.perform(delete("/api/v1/movieservice/movie/{Id}", 1)).andExpect(status().isOk());
		verify(movieService, times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(movieService);
	}

	/**
	 * test for getMovieById method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetMovieById() throws Exception {
		when(movieService.getMovieById(1)).thenReturn(movieList.get(0));
		mvc.perform(get("/api/v1/movieservice/movie/{id}", 1)).andExpect(status().isOk());
		verify(movieService, times(1)).getMovieById(1);
		verifyNoMoreInteractions(movieService);
	}

	/**
	 * test for getAllMovie method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetMyMovies() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwiaWF0IjoxNTUzMzQ5MzAyfQ.AHcy0arsIstbCZz5fOi3KvY7d4v03dPJg57ASdKlCTI";
		when(movieService.getMyMovies("user123")).thenReturn(movieList);
		mvc.perform(get("/api/v1/movieservice/movies").header("authorization", "Bearer "+token).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andDo(print());
		verify(movieService, times(1)).getMyMovies("user123");
		verifyNoMoreInteractions(movieService);
	}

	/**
	 * method to convert to string
	 * 
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */

	private static String jsonToString(final Object object) throws JsonProcessingException {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(object);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

}
