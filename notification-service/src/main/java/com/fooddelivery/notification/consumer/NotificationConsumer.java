package com.fooddelivery.notification.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fooddelivery.notification.dto.NotificationEvent;
import com.fooddelivery.notification.service.NotificationService;

@Component
public class NotificationConsumer {

	@Autowired 
	private NotificationService service;
	
	@KafkaListener(topics="notification-topic",groupId="notification-group")
	public void consume(NotificationEvent event) {
		 service.saveNotification(event);
	}
	
	@KafkaListener(topics="order-topic",groupId="order-group")
	public void consumeOrder(NotificationEvent event) {
		service.saveNotification(event);
	}
	
	@KafkaListener(topics="restaurant-topic",groupId="restaurant-group")
	public void consumeRestaurant(NotificationEvent event) {
		service.saveNotification(event);
	}
}
