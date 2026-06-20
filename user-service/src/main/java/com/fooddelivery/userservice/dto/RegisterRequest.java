package com.fooddelivery.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

	@NotBlank(message="Name is required")
	private String name;
	
	@Email(message="Invalid email format")
	@NotBlank(message="Email is required")
	private String email;
	
	@Size(min=8,message="Password must be at least 8 characters")
	private String password;
	
	@NotBlank(message="Role is required")
	private String role;
	
	@NotBlank(message="Address is required")
	private String address;
	
	@Pattern(regexp="^[6-9][0-9] {9}$", message="Enter a valid Indian mobile number")
	private String phone;

	public RegisterRequest() {
		super();
	}

	

	public RegisterRequest(@NotBlank(message = "Name is required") String name,
			@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,
			@Size(min = 8, message = "Password must be at least 8 characters") String password,
			@NotBlank(message = "Role is required") String role,
			@NotBlank(message = "Address is required") String address,
			@Pattern(regexp = "^[6-9][0-9] {9}$", message = "Enter a valid Indian mobile number") String phone) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "RegisterRequest [name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", address=" + address + ", phone=" + phone + "]";
	}
	
	
	
}
