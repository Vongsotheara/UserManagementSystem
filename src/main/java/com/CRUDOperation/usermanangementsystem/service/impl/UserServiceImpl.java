package com.CRUDOperation.usermanangementsystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.CRUDOperation.usermanangementsystem.dto.PageDTO;
import com.CRUDOperation.usermanangementsystem.dto.UserRequestDTO;
import com.CRUDOperation.usermanangementsystem.dto.UserResponseDTO;
import com.CRUDOperation.usermanangementsystem.entity.Role;
import com.CRUDOperation.usermanangementsystem.entity.User;
import com.CRUDOperation.usermanangementsystem.exception.APIException;
import com.CRUDOperation.usermanangementsystem.exception.ResourceNotFound;
import com.CRUDOperation.usermanangementsystem.mapper.UserMapper;
import com.CRUDOperation.usermanangementsystem.repository.UserRepository;
import com.CRUDOperation.usermanangementsystem.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserResponseDTO createUser(UserRequestDTO request) {
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new APIException("Email already exists: " + request.getEmail());
		}
		if(userRepository.existsByUsername(request.getUsername())) {
			throw new APIException("Username alreay exists: " + request.getUsername());
		}
		User user = userMapper.toEntity(request);
		if (user.getActive() == null) user.setActive(true);
        if (user.getRole() == null) user.setRole(Role.USER);
        return userMapper.toDTO(userRepository.save(user));
		
	}

	@Override
	public UserResponseDTO getUserById(Long id) {
		User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found with id: " + id));
        return userMapper.toDTO(user);
	}

	@Override
	public PageDTO<UserResponseDTO> getAllUsers(int page, int size) {
		Page<User> userPage = userRepository.findAll(PageRequest.of(page, size));
        List<UserResponseDTO> content = userPage.getContent()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
        PageDTO<UserResponseDTO> pageDTO = new PageDTO<>();
        pageDTO.setContent(content);
        pageDTO.setPageNumber(userPage.getNumber());
        pageDTO.setPageSize(userPage.getSize());
        pageDTO.setTotalElements(userPage.getTotalElements());
        pageDTO.setTotalPages(userPage.getTotalPages());
        return pageDTO;
	}

	@Override
	public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
		 User user = userRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFound("User not found with id: " + id));
	        if (request.getEmail() != null &&
	            !request.getEmail().equals(user.getEmail()) &&
	            userRepository.existsByEmail(request.getEmail())) {
	            throw new APIException("Email already exists: " + request.getEmail());
	        }
	        userMapper.updateEntity(request, user);
	        return userMapper.toDTO(userRepository.save(user));
	}

	@Override
	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
            throw new ResourceNotFound("User not found with id: " + id);
        }
        userRepository.deleteById(id);
		
	}

	

	
	

}
