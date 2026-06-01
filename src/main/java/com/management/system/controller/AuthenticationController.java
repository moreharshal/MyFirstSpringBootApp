package com.management.system.controller;


import com.management.system.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginBean json) {
        if (json == null || isBlank(json.getUsername()) || isBlank(json.getPassword())) {
            return ResponseEntity.badRequest().body(
                    new AuthResponse(false, "Username and password are required.", null, null)
            );
        }

        boolean authenticated = authenticationService.authenticate(
                json.getUsername(),
                json.getPassword()
        );

        if (authenticated) {
            String token = authenticationService.generateSessionToken(json.getUsername());
            AuthResponse response = new AuthResponse(
                    true,
                    "Login successful.",
                    json.getUsername(),
                    LocalDateTime.now().toString(),
                    token
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
