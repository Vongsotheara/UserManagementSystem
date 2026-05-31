package com.CRUDOperation.usermanangementsystem.service;

import com.CRUDOperation.usermanangementsystem.dto.PageDTO;
import com.CRUDOperation.usermanangementsystem.dto.UserRequestDTO;
import com.CRUDOperation.usermanangementsystem.dto.UserResponseDTO;

public interface UserService {
	
	UserResponseDTO createUser(UserRequestDTO request); //create new user
	UserResponseDTO getUserById(Long id); //get 1 user
	PageDTO<UserResponseDTO> getAllUsers(int page, int size); 
	UserResponseDTO updateUser(Long id, UserRequestDTO request); 
	void deleteUser(Long id);
	
	//restore the deleted user
	UserResponseDTO restoreUser(Long id);
	

}
