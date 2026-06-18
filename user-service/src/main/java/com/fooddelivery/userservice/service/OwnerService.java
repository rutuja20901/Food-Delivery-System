package com.fooddelivery.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fooddelivery.userservice.dto.ChangePasswordRequest;
import com.fooddelivery.userservice.dto.GetProfileResponse;
import com.fooddelivery.userservice.dto.UpdateProfileRequest;
import com.fooddelivery.userservice.dto.UpdateOwnerProfileResponse;
import com.fooddelivery.userservice.entity.User;
import com.fooddelivery.userservice.repository.OwnerRepository;

@Service
public class OwnerService {

	private static final String PROFILE_UPDATED = "Profile Updated Successfully";

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	/*
     * BUSINESS LOGIC :
     * Get authenticated owner's email
     * Fetch owner details from database
     * Map entity data to response DTO
     * Return owner profile information
     */
	public GetProfileResponse ownerProfile() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User owner = ownerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Owner Not Found!"));
		GetProfileResponse response = new GetProfileResponse();
		response.setId(owner.getId());
		response.setName(owner.getName());
		response.setEmail(owner.getEmail());
		response.setPhone(owner.getPhone());
		response.setRole(owner.getRole());
		return response;

	}

	
	/*
     * BUSINESS LOGIC :
     * Get authenticated owner
     * Update profile details
     * Save updated information
     * Return success response
     */
	public UpdateOwnerProfileResponse updateProfile(UpdateProfileRequest request) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User owner = ownerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Owner Not Found!"));
		owner.setEmail(request.getEmail());
		owner.setName(request.getName());
		owner.setPhone(request.getPhone());
		ownerRepository.save(owner);

		UpdateOwnerProfileResponse response = new UpdateOwnerProfileResponse();
		response.setMessage(PROFILE_UPDATED);

		return response;
	}

	
	/*
     * BUSINESS LOGIC :
     * Get authenticated owner
     * Validate old password
     * Encode new password
     * Update password in database
     * Return success message
     */
	public String changePassword(ChangePasswordRequest request) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = ownerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Owner is not found!"));

		boolean matches = passwordEncoder.matches(request.getNewPassword(), user.getPassword());
		if (!matches) {
			throw new RuntimeException("Passwords are not matched!");
		}

		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		ownerRepository.save(user);
		return "Password Change Successfully!";
	}

	
	 /*
     * BUSINESS LOGIC :
     * Find owner by ID
     * Validate owner existence
     * Delete owner account
     * Return success message
     */
	public String deleteProfile(Long id) {
		User owner = ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner is not found!"));
		ownerRepository.delete(owner);
		return "Owner Deleted Successfully";
	}
}
