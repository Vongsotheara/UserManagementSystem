package com.CRUDOperation.usermanangementsystem.exception;

@SuppressWarnings("serial")
public class APIException extends RuntimeException{
	public APIException(String message) {
		super(message);
	}
	

}
//Used when: bad input or business rule broken
//Example: "Email already exists"
//Returns: HTTP 400 Bad Request