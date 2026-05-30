package com.CRUDOperation.usermanangementsystem.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

//this class just s a simple data container — 
//its only job is to hold the error information that gets sent back to the client as JSON.
@Data
@AllArgsConstructor	//auto generate constructors
public class ErrorResponse {

	private int status;	// HTTP code: 400, 404, 500
	private String message; // Human readable: "User not found"
	private LocalDateTime timestamp; // When the error happened
	
}
