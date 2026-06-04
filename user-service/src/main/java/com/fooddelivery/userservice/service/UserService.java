package com.fooddelivery.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fooddelivery.userservice.dto.ChangePasswordRequest;
import com.fooddelivery.userservice.dto.UpdateProfileRequest;
import com.fooddelivery.userservice.dto.GetProfileResponse;
import com.fooddelivery.userservice.entity.User;
import com.fooddelivery.userservice.repository.UserRepository;

@Service
public class UserService {

	/*
	 * users APIs: GET /users/profile - done PUT /users/profile PUT
	 * /users/change-password DELETE /users/account
	 */
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public GetProfileResponse userProfile() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found!"));
		GetProfileResponse response = new GetProfileResponse();
		response.setId(user.getId());
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setPhone(user.getPhone());
		response.setRole(user.getRole());
		return response;

	}

	public GetProfileResponse updateProfile(UpdateProfileRequest request) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found!"));
		user.setEmail(request.getEmail());
		user.setName(request.getName());
		user.setPhone(request.getPhone());
		User updatedProfile = userRepository.save(user);

		GetProfileResponse response = new GetProfileResponse();
		response.setId(user.getId());
		response.setRole(user.getRole());
		response.setEmail(updatedProfile.getEmail());
		response.setName(updatedProfile.getName());
		response.setPhone(updatedProfile.getPhone());

		return response;
	}

	public String changePassword(ChangePasswordRequest request) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User is not found!"));
		
		boolean matches = passwordEncoder.matches(request.getNewPassword(), request.getOldPassword());
		if(!matches) {
			throw new RuntimeException("Passwords are not matched!");
		}
		
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		return "Password Change Successfully!";
	}
	
	
	public String deleteProfile(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User is not found!"));
		userRepository.delete(user);
		return "User Deleted Successfully";
	}
}
