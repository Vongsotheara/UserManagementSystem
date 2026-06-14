package com.CRUDOperation.usermanangementsystem.AuthDTOs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CRUDOperation.usermanangementsystem.AuthDTOs.dto.AuthResponse;
import com.CRUDOperation.usermanangementsystem.AuthDTOs.dto.LoginRequest;
import com.CRUDOperation.usermanangementsystem.AuthDTOs.dto.RegisterRequest;
import com.CRUDOperation.usermanangementsystem.entity.Role;
import com.CRUDOperation.usermanangementsystem.entity.User;
import com.CRUDOperation.usermanangementsystem.exception.APIException;
import com.CRUDOperation.usermanangementsystem.exception.ResourceNotFound;
import com.CRUDOperation.usermanangementsystem.mapper.UserMapper;
import com.CRUDOperation.usermanangementsystem.repository.UserRepository;
import com.CRUDOperation.usermanangementsystem.security.JwtUtil;

@Service
public class AuthService {
	@Autowired private AuthenticationManager authenticationManager;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserMapper userMapper;

    public AuthResponse login(LoginRequest request) {
        // Triggers Spring Security's full authentication flow:
        // loads user → checks password → throws exception if wrong
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsernameAndDeletedFalse(request.getUsername())
                .orElseThrow(() -> new ResourceNotFound("User not found"));

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsernameAndDeletedFalse(request.getUsername()))
            throw new APIException("Username already exists");
        if (userRepository.existsByEmailAndDeletedFalse(request.getEmail()))
            throw new APIException("Email already exists");

        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // hash the password!
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setAge(request.getAge());
        user.setGender(request.getGender());
        user.setRole(request.getRole() != null ? request.getRole() : Role.USER);
        user.setActive(true);
        user.setDeleted(false);

        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }
}
