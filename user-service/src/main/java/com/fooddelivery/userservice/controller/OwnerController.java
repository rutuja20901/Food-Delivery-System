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
import com.fooddelivery.userservice.dto.GetProfileResponse;
import com.fooddelivery.userservice.dto.UpdateOwnerProfileResponse;
import com.fooddelivery.userservice.dto.UpdateProfileRequest;
import com.fooddelivery.userservice.service.OwnerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name="Restaurant Owner APIs")
@RestController
@RequestMapping("/owners")
public class OwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	@Operation(summary="Get Profile")
	@GetMapping("/profile")
	public ResponseEntity<GetProfileResponse> ownerProfile() {
		return ResponseEntity.ok(ownerService.ownerProfile());
	}
	
	@Operation(summary="Update Profile")
	@PutMapping("/profile")
	public ResponseEntity<UpdateOwnerProfileResponse> updateProfile(@Valid @RequestBody UpdateProfileRequest request){
		return ResponseEntity.ok(ownerService.updateProfile(request));
	}
	
	@Operation(summary="Change Password")
	@PutMapping("/change-password")
	public String changePassword(@Valid @RequestBody ChangePasswordRequest request){
		return ownerService.changePassword(request);
	}
	
	@Operation(summary="Delete Profile by Id")
	@DeleteMapping("/{id}")
	public String deleteProfile(Long id){
		return ownerService.deleteProfile(id);
	}
}

