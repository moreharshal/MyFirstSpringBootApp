package com.management.system.controller;

import com.management.system.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/session")
public class SessionController {

    private final AuthenticationService authenticationService;

    @Autowired
    public SessionController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/validate")
    public ResponseEntity<SessionValidationResponse> validateSession(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(
                    new SessionValidationResponse(false, null, "No valid authorization token provided")
            );
        }

        String token = authHeader.substring(7); // Remove "Bearer " prefix
        String username = authenticationService.validateToken(token);

        if (username != null) {
            return ResponseEntity.ok(
                    new SessionValidationResponse(true, username, "Session valid")
            );
        }

        return ResponseEntity.status(401).body(
                new SessionValidationResponse(false, null, "Invalid or expired session")
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            authenticationService.invalidateToken(token);
        }

        return ResponseEntity.ok().build();
    }
}
