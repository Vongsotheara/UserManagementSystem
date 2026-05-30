package com.CRUDOperation.usermanangementsystem.dto;

import java.time.LocalDateTime;

import com.CRUDOperation.usermanangementsystem.entity.Role;

import lombok.Data;
@Data
public class UserResponseDTO {
//this class is the API field send back to the client, there's no password to show to the client
	private Long id;
    private String name;
    private String email;
    private String phone;
    private String username;
    private String address;
    private Integer age;
    private String gender;
    private Role role;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
