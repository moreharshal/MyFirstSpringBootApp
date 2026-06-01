package com.management.system.service;

public interface AuthenticationService {
    /**
     * Authenticates a user with username and password
     * @param username the username
     * @param password the password
     * @return true if authentication is successful, false otherwise
     */
    boolean authenticate(String username, String password);

    /**
     * Generates a session token for authenticated user
     * @param username the username
     * @return session token
     */
    String generateSessionToken(String username);

    /**
     * Validates a session token
     * @param token the session token
     * @return username if token is valid, null otherwise
     */
    String validateToken(String token);

    /**
     * Invalidates a session token (logout)
     * @param token the session token
     */
    void invalidateToken(String token);
}
