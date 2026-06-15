package com.fooddelivery.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.userservice.dto.LoginRequest;
import com.fooddelivery.userservice.dto.LoginResponse;
import com.fooddelivery.userservice.dto.RegisterRequest;
import com.fooddelivery.userservice.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Authentication APIs")
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
	@Operation(summary="Register API")
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
	@Operation(summary="Login API")
	@PostMapping("/login")
	public LoginResponse login(
	        @RequestBody LoginRequest request) {

	    return authService.login(request);

	}
	
	
	
	
	
	
}
