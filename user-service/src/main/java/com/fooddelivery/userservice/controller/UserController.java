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
import com.fooddelivery.userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return userService.registerUser(request);
    }
	
	
	@PostMapping("/login")
	public LoginResponse login(
	        @RequestBody LoginRequest request) {

	    return userService.login(request);

	}
	
	
	@GetMapping("/profile")
	public String profile(){

	    return "Protected API Success";
	}
	
	@GetMapping("/customer")
	public String customer() {
		return "Customer entry only";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "Admin entry only";
	}
	
	@GetMapping("/restaurant")
	public String restaurant() {
		return "Restaurant entry only";
	}
	
	
	
}
