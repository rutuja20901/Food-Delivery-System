package com.fooddelivery.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.userservice.dto.ChangePasswordRequest;
import com.fooddelivery.userservice.dto.UpdateProfileRequest;
import com.fooddelivery.userservice.dto.UserProfileResponse;
import com.fooddelivery.userservice.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/profile")
	public ResponseEntity<UserProfileResponse> userProfile() {
		return ResponseEntity.ok(userService.userProfile());
	}
	
	@PutMapping("/profile")
	public ResponseEntity<UserProfileResponse> updateProfile(@Valid @RequestBody UpdateProfileRequest request){
		return ResponseEntity.ok(userService.updateProfile(request));
	}
	
	@PutMapping("/change-password")
	public String changePassword(@Valid @RequestBody ChangePasswordRequest request){
		return userService.changePassword(request);
	}
	
	@DeleteMapping("/{id}")
	public String deleteProfile(Long id){
		return userService.deleteProfile(id);
	}
}
