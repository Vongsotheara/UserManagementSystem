package com.CRUDOperation.usermanangementsystem.AuthDTOs.dto;

import com.CRUDOperation.usermanangementsystem.entity.Role;

import lombok.Data;

@Data
public class RegisterRequest {
	private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Integer age;
    private String gender;
    private Role role;
}
