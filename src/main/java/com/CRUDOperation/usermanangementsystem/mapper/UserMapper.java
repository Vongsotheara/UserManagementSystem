package com.CRUDOperation.usermanangementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.CRUDOperation.usermanangementsystem.dto.UserRequestDTO;
import com.CRUDOperation.usermanangementsystem.dto.UserResponseDTO;
import com.CRUDOperation.usermanangementsystem.entity.User;

//Without componentModel = "spring" you cannot use @Autowired to inject the mapper — 
//you would have to call it manually like UserMapper.INSTANCE.
@Mapper(componentModel = "spring")

public interface UserMapper {
	User toEntity(UserRequestDTO dto); //Convert ClientData to DB object, use when saving a new user
	
	//UserRequestDTO dto: Takes the incoming request data from the client
	//User: Converts and returns a User entity ready to save to the database
	UserResponseDTO  toDTO(User user);
	
	//UserRequestDTO dto: The new data coming from the client
	//@MappingTarget User: The existing entity to update — MapStruct writes new values into this object
	void updateEntity(UserRequestDTO dto, @MappingTarget User user);

	
}
