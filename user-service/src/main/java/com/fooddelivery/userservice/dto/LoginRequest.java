package com.fooddelivery.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

	@Email(message="Invalid email format")
	@NotBlank(message="Email is required")
	private String email;
	
	@Size(min=8,message="Password must be at least 8 characters")
	private String password;

	public LoginRequest() {
		super();
	}

	

	public LoginRequest(@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,
			@Size(min = 8, message = "Password must be at least 8 characters") String password) {
		super();
		this.email = email;
		this.password = password;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [email=" + email + ", password=" + password + "]";
	}
	
	
}
