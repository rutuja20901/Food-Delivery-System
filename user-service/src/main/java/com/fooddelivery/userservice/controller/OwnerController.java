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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/owners")
public class OwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	
	@GetMapping("/profile")
	public ResponseEntity<GetProfileResponse> ownerProfile() {
		return ResponseEntity.ok(ownerService.ownerProfile());
	}
	
	@PutMapping("/profile")
	public ResponseEntity<UpdateOwnerProfileResponse> updateProfile(@Valid @RequestBody UpdateProfileRequest request){
		return ResponseEntity.ok(ownerService.updateProfile(request));
	}
	
	@PutMapping("/change-password")
	public String changePassword(@Valid @RequestBody ChangePasswordRequest request){
		return ownerService.changePassword(request);
	}
	
	@DeleteMapping("/{id}")
	public String deleteProfile(Long id){
		return ownerService.deleteProfile(id);
	}
}

