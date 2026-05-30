package com.CRUDOperation.usermanangementsystem.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Decide how to respond


@RestControllerAdvice	//look the entire app for error, 
					//if there's an error this class will return proper response instead of crashing
public class GlobalExceptionHandler {

	
//When a user requests a record that doesn't exist (e.g. GET /users/999).
	@ExceptionHandler(ResourceNotFound.class)	//when the exception is thrown, it'll automatically call 
												//this method with Specifies which exception this method should catch
		public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFound ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorResponse(404, ex.getMessage(), LocalDateTime.now()));
		}
	
//Invalid input, business rule violations, or bad request data (e.g. submitting a negative price).
	@ExceptionHandler(APIException.class)
	public ResponseEntity<ErrorResponse> handleAPIException(APIException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(400, ex.getMessage(), LocalDateTime.now()));
	}
	
	@ExceptionHandler(Exception.class)
//Unexpected failures like database connection issues, null pointers
	public ResponseEntity<ErrorResponse> handleGeneral(Exception ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(500, ex.getMessage(), LocalDateTime.now()));
	}
	
}


	

