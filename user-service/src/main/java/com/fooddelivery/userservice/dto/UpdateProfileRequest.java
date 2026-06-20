package com.fooddelivery.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdateProfileRequest {
	
	@NotBlank(message="Name is required")
	private String name;
	
	@Email(message="Invalid email format")
	@NotBlank(message="Email is required")
	private String email;
	
	@Pattern(regexp="^[6-9][0-9] {9}$", message="Enter a valid Indian mobile number")
	private String phone;

	public UpdateProfileRequest() {
		super();
	}

	

	public UpdateProfileRequest(@NotBlank(message = "Name is required") String name,
			@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,
			@Pattern(regexp = "^[6-9][0-9] {9}$", message = "Enter a valid Indian mobile number") String phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
