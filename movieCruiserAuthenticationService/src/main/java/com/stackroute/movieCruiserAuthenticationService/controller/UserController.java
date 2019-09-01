package com.stackroute.movieCruiserAuthenticationService.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.movieCruiserAuthenticationService.domain.User;
import com.stackroute.movieCruiserAuthenticationService.exception.UserAlreadyExistsException;
import com.stackroute.movieCruiserAuthenticationService.service.SecurityTokenGenerator;
import com.stackroute.movieCruiserAuthenticationService.service.UserService;

@RestController
@EnableWebMvc
@RequestMapping("/api/v1/userservice")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private SecurityTokenGenerator securityTokenGenerator;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("User Registered Successfully",HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}",HttpStatus.CONFLICT);
		}
	}
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetails){
		
		try {
			if(loginDetails.getUserId()==null || loginDetails.getPassword()==null) {
				throw new Exception("Username or password cannot be Empty");
			}
			User user = userService.findByUserIdAndPassword(loginDetails.getUserId(), loginDetails.getPassword());
			if(user==null) {
				throw new Exception("User with given Id does not exists");
			}
			if(!loginDetails.getPassword().equals(user.getPassword())) {
				throw new Exception("Invalid Login Credential ,PLease check Usename and password");
			}
			Map<String, String> map = securityTokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}",HttpStatus.UNAUTHORIZED);
		}
	}

}
