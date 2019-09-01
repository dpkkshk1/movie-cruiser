package com.stackroute.movieCruiserAuthenticationService.service;

import com.stackroute.movieCruiserAuthenticationService.domain.User;
import com.stackroute.movieCruiserAuthenticationService.exception.UserAlreadyExistsException;
import com.stackroute.movieCruiserAuthenticationService.exception.UserNotFoundException;

public interface UserService {
	/**
	 * 
	 * method to Save user to database
	 * 
	 * @param user
	 * @return
	 * @throws UserAlreadyExistsException
	 */
	boolean saveUser(User user) throws UserAlreadyExistsException;
	/**
	 * 
	 * method to authenticate user
	 * 
	 * @param userId
	 * @param Password
	 * @return
	 * @throws UserNotFoundException
	 */
	User findByUserIdAndPassword(String userId,String Password) throws UserNotFoundException;
}
