package com.stackroute.moviecruiserserverapplication.exception;

public class MovieNotFoundException extends Exception {
	/**
	 * message of exception
	 */
	private String message;

	public MovieNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MovieNotFoundException(final String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "MovieNotFoundException [message=" + message + "]";
	}

}
