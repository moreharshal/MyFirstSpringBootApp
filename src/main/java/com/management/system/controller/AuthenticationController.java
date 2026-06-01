package com.management.system.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/authenticate")
public class AuthenticationController {

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginBean json, HttpSession session) {
        if (json == null || isBlank(json.getUsername()) || isBlank(json.getPassword())) {
            return ResponseEntity.badRequest().body(
                    new AuthResponse(false, "Username and password are required.", null, null)
            );
        }

        // Retrieve credentials from environment variables or configuration
        String validUsername = System.getenv("AUTH_USERNAME");
        String validPassword = System.getenv("AUTH_PASSWORD");

        // Fallback to application properties if environment variables are not set
        if (isBlank(validUsername)) {
            validUsername = "admin"; // Default for development only
        }
        if (isBlank(validPassword)) {
            validPassword = "admin123"; // Default for development only
        }

        if (validUsername.equals(json.getUsername()) && validPassword.equals(json.getPassword())) {
            // Store authenticated user in session
            session.setAttribute("authenticated_user", json.getUsername());
            session.setAttribute("login_time", LocalDateTime.now().toString());

            AuthResponse response = new AuthResponse(
                    true,
                    "Login successful.",
                    json.getUsername(),
                    LocalDateTime.now().toString()
            );
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body(
                new AuthResponse(false, "Invalid username or password.", json.getUsername(), null)
        );
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public ResponseEntity<AuthResponse> verifySession(HttpSession session) {
        String authenticatedUser = (String) session.getAttribute("authenticated_user");
        String loginTime = (String) session.getAttribute("login_time");

        if (authenticatedUser != null) {
            return ResponseEntity.ok(new AuthResponse(
                    true,
                    "Session valid.",
                    authenticatedUser,
                    loginTime
            ));
        }

        return ResponseEntity.status(401).body(
                new AuthResponse(false, "Not authenticated.", null, null)
        );
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
