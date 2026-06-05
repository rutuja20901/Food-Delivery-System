package com.fooddelivery.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.userservice.dto.ActivateDeactivate;
import com.fooddelivery.userservice.dto.MessageResponse;
import com.fooddelivery.userservice.dto.UserResponseDto;
import com.fooddelivery.userservice.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	
	@PutMapping("/users/{id}/active")
	public ResponseEntity<MessageResponse> activateUser(@Valid @RequestBody ActivateDeactivate request) {
		return ResponseEntity.ok(adminService.activateUser(request));
	}
	
	@PutMapping("/users/{id}/deactive")
	public ResponseEntity<MessageResponse> deactivateUser(@Valid @RequestBody ActivateDeactivate request) {
		return ResponseEntity.ok(adminService.deactivateUser(request));
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDto>> getAllUsers(){
		return ResponseEntity.ok(adminService.getAllUsers());
	}
}
