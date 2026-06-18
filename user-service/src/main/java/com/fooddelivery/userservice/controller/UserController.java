package com.fooddelivery.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.userservice.dto.ChangePasswordRequest;
import com.fooddelivery.userservice.dto.GetProfileResponse;
import com.fooddelivery.userservice.dto.UpdateProfileRequest;
import com.fooddelivery.userservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User APIs")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	
	/*
	 * API : GET USER PROFILE
	 * Retrieve logged-in user's profile details
	 * Returns user information
	 */
	@Operation(summary = "Get User Profile")
	@GetMapping("/profile")
	public ResponseEntity<GetProfileResponse> userProfile() {
		return ResponseEntity.ok(userService.userProfile());
	}

	
	/*
	 * API : UPDATE USER PROFILE
	 * Update logged-in user's profile information
	 * Accepts updated name, email and phone number
	 */
	@Operation(summary = "Update User Profile")
	@PutMapping("/profile")
	public ResponseEntity<GetProfileResponse> updateProfile(@Valid @RequestBody UpdateProfileRequest request) {
		return ResponseEntity.ok(userService.updateProfile(request));
	}

	
	/*
	 * API : CHANGE USER PASSWORD
	 * Validate old password
	 * Update user's password
	 */
	@Operation(summary = "Update Password for User")
	@PutMapping("/change-password")
	public String changePassword(@Valid @RequestBody ChangePasswordRequest request) {
		return userService.changePassword(request);
	}

	
	/*
	 * API : DELETE USER PROFILE
	 * Delete user account by ID
	 * Remove user record from database
	 */
	@Operation(summary = "Delete Profile by Id")
	@DeleteMapping("/{id}")
	public String deleteProfile(@PathVariable Long id) {
		return userService.deleteProfile(id);
	}
}
