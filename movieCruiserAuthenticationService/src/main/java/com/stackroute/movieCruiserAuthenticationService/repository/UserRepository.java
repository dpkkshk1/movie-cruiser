package com.stackroute.movieCruiserAuthenticationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.movieCruiserAuthenticationService.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	User findByUserIdAndPassword(String userId,String password);
}
