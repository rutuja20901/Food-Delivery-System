package com.fooddelivery.userservice.dto;

import jakarta.validation.constraints.Size;

public class ChangePasswordRequest {
	
	@Size(min=8,message="Password must be at least 8 characters")
	private String oldPassword;
	
	@Size(min=8,message="Password must be at least 8 characters")
	private String newPassword;

	public ChangePasswordRequest() {
		super();
	}

	

	public ChangePasswordRequest(@Size(min = 8, message = "Password must be at least 8 characters") String oldPassword,
			@Size(min = 8, message = "Password must be at least 8 characters") String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}



	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
