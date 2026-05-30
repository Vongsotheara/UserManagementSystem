package com.CRUDOperation.usermanangementsystem.exception;
//Signal something went wrong
@SuppressWarnings("serial")
public class ResourceNotFound extends RuntimeException{
	public ResourceNotFound(String message) {
		super(message);
	}

}
//Used when: bad input or business rule broken
//Example: "Email already exists"
//Returns: HTTP 400 Bad Request