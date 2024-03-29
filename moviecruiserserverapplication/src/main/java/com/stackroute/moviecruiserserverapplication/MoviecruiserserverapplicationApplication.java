package com.stackroute.moviecruiserserverapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.moviecruiserserverapplication.filter.JwtFilter;

@SpringBootApplication
@EnableAutoConfiguration
public class MoviecruiserserverapplicationApplication {
	
	@Bean
	public FilterRegistrationBean jwtFilter(){
		final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/*");
		return filterRegistrationBean;
	}
	public static void main(String[] args) {
		SpringApplication.run(MoviecruiserserverapplicationApplication.class, args);
		System.out.println("Hello");
	}

}
