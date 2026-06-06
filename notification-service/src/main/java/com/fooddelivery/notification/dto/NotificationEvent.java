package com.fooddelivery.notification.dto;

public class NotificationEvent {

	private Long userId;
	
	private String title;
	
	private String message;

	public NotificationEvent() {
		super();
	}

	public NotificationEvent(Long userId, String title, String message) {
		super();
		this.userId = userId;
		this.title = title;
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
