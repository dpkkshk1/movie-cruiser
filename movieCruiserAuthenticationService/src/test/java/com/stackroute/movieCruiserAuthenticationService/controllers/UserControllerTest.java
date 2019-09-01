package com.stackroute.movieCruiserAuthenticationService.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
import com.stackroute.movieCruiserAuthenticationService.controller.UserController;
import com.stackroute.movieCruiserAuthenticationService.domain.User;
import com.stackroute.movieCruiserAuthenticationService.service.SecurityTokenGenerator;
import com.stackroute.movieCruiserAuthenticationService.service.UserService;

import java.util.Date;



@RunWith(SpringRunner.class)
@WebMvcTest(UserControllerTest.class)
public class UserControllerTest {

	/**
	 * create mvc object reference
	 */
	@Autowired
	private transient MockMvc mvc;
	/**
	 * reference to user service object
	 */
	@MockBean
	private transient UserService userService;
	/**
	 * reference to token generator service object
	 */
	@MockBean
	private transient SecurityTokenGenerator securityTokenGenerator;
	/**
	 * Mock for controller
	 */
	@InjectMocks
	private transient UserController userController;
	/**
	 * reference to user object
	 */
	private transient User user;
	/**
	 * setting up requirement
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(userController).build();
		user = new User("dpkkshk", "Deepak", "Kaushik", "123456", 8684849183L, new Date());
	}
	@Test
	public void testRegisterUser()throws Exception{
		when(userService.saveUser(user)).thenReturn(true);
		mvc.perform(post("/api/v1/userservice/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(jsonToString(user)))
				.andExpect(status().isCreated()).andDo(print());
		verify(userService, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
	}
	
	@Test
	public void testLoginUser() throws Exception{
		String userId = "dpkkshk";
		String password ="123456";
		when(userService.saveUser(user)).thenReturn(true);
		when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
		mvc.perform(post("/api/v1/userservice/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user)))
				.andExpect(status().isOk());
		verify(userService, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(userService);
		
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
