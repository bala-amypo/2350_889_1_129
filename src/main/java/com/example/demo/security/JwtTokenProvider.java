package com.example.demo.security;

public class JwtTokenProvider {

    public String generateToken(String email, String role, Long userId) {
        return email + "|" + role + "|" + userId + "|" + System.currentTimeMillis();
    }

    public boolean validateToken(String token) {
        return token != null && token.contains("|");
    }
}
