package com.fooddelivery.userservice.dto;

public class RegisterRequest {

	private String name;
	
	private String email;
	
	private String password;
	
	private String role;
	
	private String address;
	
	private String phone;

	public RegisterRequest() {
		super();
	}

	public RegisterRequest(String name, String email, String password, String role, String address, String phone) {
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
