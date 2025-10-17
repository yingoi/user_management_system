package com.vnn.usermanagement.service.impl;

import com.vnn.usermanagement.dto.request.LoginRequest;
import com.vnn.usermanagement.dto.response.JwtResponse;
import com.vnn.usermanagement.entity.User;
import com.vnn.usermanagement.repository.UserRepository;
import com.vnn.usermanagement.security.JwtService;
import com.vnn.usermanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow( () -> new RuntimeException("User not found with username: " + loginRequest.getUsername()));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }
        String accessToken = jwtService.generateToken(user.getUsername());
        String refreshToken = jwtService.refreshToken(user.getUsername());
        return new JwtResponse(accessToken, refreshToken);
    }
}
