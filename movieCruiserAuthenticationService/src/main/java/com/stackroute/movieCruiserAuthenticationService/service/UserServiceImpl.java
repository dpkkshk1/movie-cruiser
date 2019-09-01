package com.stackroute.movieCruiserAuthenticationService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.movieCruiserAuthenticationService.domain.User;
import com.stackroute.movieCruiserAuthenticationService.exception.UserAlreadyExistsException;
import com.stackroute.movieCruiserAuthenticationService.exception.UserNotFoundException;
import com.stackroute.movieCruiserAuthenticationService.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	/**
	 * user Repository object
	 */
	private final UserRepository userRepository;
	/**
	 * constructor based injection of userRepository object
	 * @param userRepository
	 */
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	/**
	 * method impl of SaveUser method
	 */
	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException{
		final Optional<User> user1 = userRepository.findById(user.getUserId());
		if(user1.isPresent()) {
			throw new UserAlreadyExistsException("User with this UserId ALredy Exists");
		}
		userRepository.save(user);
		return true;
	}
	/**
	 * method Impl of findByIdAndPassword method
	 */
	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		final User user = userRepository.findByUserIdAndPassword(userId, password);
		if(user==null) {
			throw new UserNotFoundException("UserID and Password Mismatch Please Check and Try Again");
		}
		return user;
	}

}
