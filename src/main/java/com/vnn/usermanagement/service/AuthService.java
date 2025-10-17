package com.vnn.usermanagement.service;

import com.vnn.usermanagement.dto.request.LoginRequest;
import com.vnn.usermanagement.dto.response.JwtResponse;

public interface AuthService {
    public JwtResponse login(LoginRequest loginRequest);
}
