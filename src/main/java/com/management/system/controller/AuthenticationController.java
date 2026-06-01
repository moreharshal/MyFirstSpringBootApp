package com.management.system.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/authenticate")
public class AuthenticationController {

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginBean json) {
        if (json == null || isBlank(json.getUsername()) || isBlank(json.getPassword())) {
            return ResponseEntity.badRequest().body(
                    new AuthResponse(false, "Username and password are required.", null, null)
            );
        }

        if ("admin".equals(json.getUsername()) && "admin123".equals(json.getPassword())) {
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

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
