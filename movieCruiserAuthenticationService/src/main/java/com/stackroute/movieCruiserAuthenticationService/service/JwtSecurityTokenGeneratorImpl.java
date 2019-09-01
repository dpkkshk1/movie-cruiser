package com.stackroute.movieCruiserAuthenticationService.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.movieCruiserAuthenticationService.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
	/**
	 * Jwt Token Generator Implementation
	 */
	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken="";
		jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
		Map<String,String> map = new HashMap<>();
		map.put("token", jwtToken);
		map.put("message","User Logged in SuccessFully");
		return map;
	}

}
