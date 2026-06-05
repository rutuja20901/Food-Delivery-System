package com.fooddelivery.userservice.dto;

import com.fooddelivery.userservice.enums.UserStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ActivateDeactivate {

	private Long id;
	
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	public ActivateDeactivate() {
		super();
	}

	public ActivateDeactivate(Long id, UserStatus status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	} 
	
	
}
