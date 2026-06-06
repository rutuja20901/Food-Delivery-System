package com.fooddelivery.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.notification.entity.Notification;
import com.fooddelivery.notification.repository.NotificationRepository;
import com.fooddelivery.notification.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	
	@Autowired
	private NotificationService service;
	
	@Autowired
	private NotificationRepository repo;
	
	@GetMapping("/user/{userId}")
	public List<Notification> getNotification(@PathVariable Long userId){
		return repo.findByUserId(userId);
	}
}
