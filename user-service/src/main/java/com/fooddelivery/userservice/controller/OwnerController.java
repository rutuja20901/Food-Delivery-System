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
import com.fooddelivery.userservice.dto.UpdateOwnerProfileResponse;
import com.fooddelivery.userservice.dto.UpdateProfileRequest;
import com.fooddelivery.userservice.service.OwnerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Restaurant Owner APIs")
@RestController
@RequestMapping("/owners")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;

	
	/*
     * API : GET OWNER PROFILE
     * Retrieve logged-in owner's profile details
     * Returns owner information
     */
	@Operation(summary = "Get Profile")
	@GetMapping("/profile")
	public ResponseEntity<GetProfileResponse> ownerProfile() {
		return ResponseEntity.ok(ownerService.ownerProfile());
	}

	
	/*
     * API : UPDATE OWNER PROFILE
     * Update logged-in owner's profile information
     * Accepts updated name, email and phone number
     */
	@Operation(summary = "Update Profile")
	@PutMapping("/profile")
	public ResponseEntity<UpdateOwnerProfileResponse> updateProfile(@Valid @RequestBody UpdateProfileRequest request) {
		return ResponseEntity.ok(ownerService.updateProfile(request));
	}

	
	/*
     * API : CHANGE PASSWORD
     * Change password for logged-in owner
     * Validates old password before updating
     */
	@Operation(summary = "Change Password")
	@PutMapping("/change-password")
	public String changePassword(@Valid @RequestBody ChangePasswordRequest request) {
		return ownerService.changePassword(request);
	}

	
	/*
     * API : DELETE OWNER PROFILE
     * Delete owner account by ID
     * Removes owner record from database
     */
	@Operation(summary = "Delete Profile by Id")
	@DeleteMapping("/{id}")
	public String deleteProfile(@PathVariable Long id) {
		return ownerService.deleteProfile(id);
	}
}
