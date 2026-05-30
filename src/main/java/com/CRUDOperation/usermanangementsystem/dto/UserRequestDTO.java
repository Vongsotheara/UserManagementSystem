package com.CRUDOperation.usermanangementsystem.dto;

import com.CRUDOperation.usermanangementsystem.entity.Role;

import lombok.Data;

//Client sends to API when creating or updating a user
@Data
public class UserRequestDTO {
	
	private String name;
	private String email;
	private String phone;
	private String username;
	private String password;
	private String address;
	private Integer age;
	private String gender;
	private Role role;
	private Boolean active;

}
