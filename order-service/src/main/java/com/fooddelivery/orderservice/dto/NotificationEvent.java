package com.fooddelivery.orderservice.dto;

public class NotificationEvent {
private Long userId;
	
private Long id;
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

	
	public NotificationEvent(Long userId, Long id, String title, String message) {
		super();
		this.userId = userId;
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
