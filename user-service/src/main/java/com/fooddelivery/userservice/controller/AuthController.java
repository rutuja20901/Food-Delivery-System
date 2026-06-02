package com.fooddelivery.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.userservice.dto.LoginRequest;
import com.fooddelivery.userservice.dto.LoginResponse;
import com.fooddelivery.userservice.dto.RegisterRequest;
import com.fooddelivery.userservice.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	
	/*
	 * API : REGISTER USER
	 * Endpoint to create a new user account
	 * Accepts user registration details
	 * Delegates request to service layer
	 */
	@PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return authService.registerUser(request);
    }
	
	/*
	 * API : USER LOGIN
	 * Authenticate user credentials
	 * Generate JWT token upon successful login
	 * Return authentication response
	 */
	@PostMapping("/login")
	public LoginResponse login(
	        @RequestBody LoginRequest request) {

	    return authService.login(request);

	}
	
	
	
	
	
	
}
