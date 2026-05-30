package com.CRUDOperation.usermanangementsystem.dto;

import java.util.List;

import lombok.Data;

//API sends back to the request as a list of users with the extra info.
@Data

//Pagination Wrapper
public class PageDTO<T> {
	
	private List<T> content; //T: any type, this case is UserResponseDTO
	private int pageNumber; // Current page (starts at 0)
	private int pageSize;	// How many per page
	private long totalElements;	// Total users in database
	private int totalPages;	// Total number of pages
	

}
