package com.stackroute.movieCruiserAuthenticationService.services;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.movieCruiserAuthenticationService.domain.User;
import com.stackroute.movieCruiserAuthenticationService.exception.UserAlreadyExistsException;
import com.stackroute.movieCruiserAuthenticationService.exception.UserNotFoundException;
import com.stackroute.movieCruiserAuthenticationService.repository.UserRepository;
import com.stackroute.movieCruiserAuthenticationService.service.UserServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class UserServiceTest {
	@Mock
	private transient UserRepository userRepository;
	@Mock
	private transient User user;
	@InjectMocks
	private transient UserServiceImpl userServiceImpl;
	
	private transient Optional<User> options;
	
	@Before
	public void setupMock () {
		MockitoAnnotations.initMocks(this);
		user =  new User ("dpkkshk","Deepak","Kaushik","123456",8684849183L,new Date());
		options = Optional.of(user);
	}
	@Test
	public void testUserSaveSuccess() throws UserAlreadyExistsException{
		when(userRepository.save(user)).thenReturn(user);
		final boolean flag = userServiceImpl.saveUser(user);
		assertTrue("Save Movie Failed call to userimpl is flase check this ",flag);
		verify(userRepository,times(1)).save(user);
	}
	@Test(expected = UserAlreadyExistsException.class)
	public void testUserSaveFailure() throws UserAlreadyExistsException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		final boolean flag = userServiceImpl.saveUser(user);
		assertFalse("test Case failed", flag);
		verify(userRepository, times(1)).findById(user.getUserId());
	}
	@Test
	public void testValidateSuccess() throws UserNotFoundException {
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals("dpkkshk", userResult.getUserId());
		verify(userRepository,times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
	@Test(expected=UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException {
		when(userRepository.findById("dpkk")).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
}
