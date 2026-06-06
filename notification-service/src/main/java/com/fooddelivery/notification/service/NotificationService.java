package com.fooddelivery.notification.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.notification.dto.NotificationEvent;
import com.fooddelivery.notification.entity.Notification;
import com.fooddelivery.notification.repository.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepo;
	
	public void saveNotification(NotificationEvent event) {
		if(event == null) {
			new RuntimeException("Notification event cannot be null");
		}
		
		Notification notification = new Notification();
		
		notification.setUserId(event.getUserId());
		notification.setTitle(event.getTitle());
		notification.setMessage(event.getMessage());
		notification.setRead(false);
		notification.setCreatedAt(LocalDateTime.now());
		notificationRepo.save(notification);
	}
	
	
}
