package com.management.system.controller;

public class LoginBean {
	
	String username;
	String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() { 
		return "username "+ username +" password " + password;
	}
	
}
