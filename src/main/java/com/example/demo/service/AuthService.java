package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;

public interface AuthService {
    
    /**
     * Register a new user
     * @param request the registration request containing email, password, and role
     * @return the authentication response with JWT token
     * @throws IllegalArgumentException if email already exists
     */
    AuthResponse register(RegisterRequest request);
    
    /**
     * Login a user
     * @param request the login request containing email and password
     * @return the authentication response with JWT token
     * @throws IllegalArgumentException if credentials are invalid
     */
    AuthResponse login(AuthRequest request);
}