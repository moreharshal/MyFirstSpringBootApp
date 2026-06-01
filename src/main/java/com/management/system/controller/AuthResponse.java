package com.management.system.controller;

public class AuthResponse {

    private boolean success;
    private String message;
    private String username;
    private String loginTime;
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(boolean success, String message, String username, String loginTime) {
        this.success = success;
        this.message = message;
        this.username = username;
        this.loginTime = loginTime;
    }

    public AuthResponse(boolean success, String message, String username, String loginTime, String token) {
        this.success = success;
        this.message = message;
        this.username = username;
        this.loginTime = loginTime;
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}