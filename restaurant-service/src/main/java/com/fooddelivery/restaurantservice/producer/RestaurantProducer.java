package com.fooddelivery.restaurantservice.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fooddelivery.restaurantservice.dto.NotificationEvent;

@Service
public class RestaurantProducer {

	@Autowired
	private  KafkaTemplate<String,NotificationEvent> kafkaTemplate;
	
	public void sendNotification(NotificationEvent event) {
		kafkaTemplate.send("restaurant-topic",event);
	}
	
}
