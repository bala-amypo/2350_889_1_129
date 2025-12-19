package com.example.demo.security;

import java.util.UUID;

public class JwtTokenProvider {

    public String generateToken(String email, String role, Long userId) {
        return UUID.randomUUID().toString();
    }

    public boolean validateToken(String token) {
        return token != null && !token.trim().isEmpty();
    }
}
