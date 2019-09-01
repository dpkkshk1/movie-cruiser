package com.stackroute.movieCruiserAuthenticationService.service;

import java.util.Map;

import com.stackroute.movieCruiserAuthenticationService.domain.User;

public interface SecurityTokenGenerator {

	Map<String,String> generateToken(User user);
}
