package com.stackroute.favouriteservice.exception;


@SuppressWarnings("serial")
public class MatchAlreadyExistsException extends Exception
{

	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MatchAlreadyExistsException(final String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "MatchAlreadyExistsException [message " + message + " ]";
	}

	
}
