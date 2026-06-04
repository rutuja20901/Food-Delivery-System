package com.fooddelivery.userservice.dto;

public class UpdateOwnerProfileResponse {

	private String message;

	public UpdateOwnerProfileResponse() {
		super();
	}

	public UpdateOwnerProfileResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
