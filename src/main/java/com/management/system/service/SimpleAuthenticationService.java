package com.management.system.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SimpleAuthenticationService implements AuthenticationService {

    // In-memory token storage (in production, use Redis or database)
    private final Map<String, String> tokenStore = new ConcurrentHashMap<>();

    // TODO: Replace with database-backed user authentication with hashed passwords
    // This is a temporary implementation for demonstration
    @Override
    public boolean authenticate(String username, String password) {
        // In production, this should query a database and verify hashed passwords
        // using BCrypt or similar secure password hashing algorithm
        return "admin".equals(username) && "admin123".equals(password);
    }

    @Override
    public String generateSessionToken(String username) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, username);
        return token;
    }

    @Override
    public String validateToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            return null;
        }
        return tokenStore.get(token);
    }

    @Override
    public void invalidateToken(String token) {
        if (token != null) {
            tokenStore.remove(token);
        }
    }
}
