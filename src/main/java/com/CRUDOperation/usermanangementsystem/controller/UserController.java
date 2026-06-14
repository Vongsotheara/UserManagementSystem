package com.CRUDOperation.usermanangementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CRUDOperation.usermanangementsystem.dto.PageDTO;
import com.CRUDOperation.usermanangementsystem.dto.UserRequestDTO;
import com.CRUDOperation.usermanangementsystem.dto.UserResponseDTO;
import com.CRUDOperation.usermanangementsystem.service.UserService;

@RestController
@RequestMapping
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/users/create")
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.createUser(request));
	}
	
	@GetMapping("/api/users/get-by/{id}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	@GetMapping("/api/users/get-all")
	public ResponseEntity<PageDTO<UserResponseDTO>> getAllUsers(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size){
		return ResponseEntity.ok(userService.getAllUsers(page, size));
	}
	@PutMapping("/api/users/update/{id}")
	public ResponseEntity<UserResponseDTO> updateUser(
			@PathVariable Long id,
			@RequestBody UserRequestDTO request){
		return ResponseEntity.ok(userService.updateUser(id, request));
	}
	@DeleteMapping("/api/users/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id)	{
		userService.deleteUser(id);
		return ResponseEntity.ok("User delete successfully");
	}
	@PatchMapping("/api/users/restore/{id}")
	public ResponseEntity<UserResponseDTO> restoreUser(@PathVariable Long id){
		return ResponseEntity.ok(userService.restoreUser(id));
	}
			
}
