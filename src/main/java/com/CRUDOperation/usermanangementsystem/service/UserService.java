package com.CRUDOperation.usermanangementsystem.service;

import com.CRUDOperation.usermanangementsystem.dto.PageDTO;
import com.CRUDOperation.usermanangementsystem.dto.UserRequestDTO;
import com.CRUDOperation.usermanangementsystem.dto.UserResponseDTO;

public interface UserService {
	
	
	UserResponseDTO getUserById(Long id);
	PageDTO<UserResponseDTO> getAllUsers(int page, int size);
	UserResponseDTO updateUser(Long id, UserRequestDTO request);
	void deleteUser(Long id);
	UserResponseDTO createUser(UserRequestDTO request);
	

}
