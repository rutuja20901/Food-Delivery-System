package com.fooddelivery.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.userservice.dto.ActivateDeactivate;
import com.fooddelivery.userservice.dto.MessageResponse;
import com.fooddelivery.userservice.dto.UserResponseDto;
import com.fooddelivery.userservice.entity.User;
import com.fooddelivery.userservice.enums.UserStatus;
import com.fooddelivery.userservice.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	public MessageResponse activateUser(ActivateDeactivate request) {
		
		User user = adminRepository.findById(request.getId())
		        .orElseThrow(() -> new RuntimeException("User not found"));

		user.setUserStatus(UserStatus.ACTIVE);
		adminRepository.saveAndFlush(user);
		MessageResponse response = new MessageResponse();
		
		response.setSuccess(true);
		response.setMessage("User Account Activated Successfully");
		
		return response;
		
	}
	
	public MessageResponse deactivateUser(ActivateDeactivate request) {
		User user = adminRepository.findById(request.getId())
		        .orElseThrow(() -> new RuntimeException("User not found"));

		user.setUserStatus(UserStatus.DEACTIVE);
		adminRepository.saveAndFlush(user);
		
		MessageResponse response = new MessageResponse();
		
		response.setSuccess(false);
		response.setMessage("User Account Deactivated Successfully");
		
		return response;
		
	}
	
	
		public List<UserResponseDto> getAllUsers() {

			 List<User> users = adminRepository.findAll();

			    if (users.isEmpty()) {
			        throw new RuntimeException("No users found");
			    }

			    return users.stream()
			            .map(this::mapToResponseDto)
			            .toList();
		}
		
		public UserResponseDto mapToResponseDto(User user) {
			UserResponseDto dto = new UserResponseDto();

		    dto.setId(user.getId());
		    dto.setFullName(user.getName());
		    dto.setEmail(user.getEmail());
		    dto.setMobileNumber(user.getPhone());
		    dto.setRole(user.getRole());
		  
		    dto.setStatus(user.getUserStatus().name());
		    

		    return dto;
		}
	

}
