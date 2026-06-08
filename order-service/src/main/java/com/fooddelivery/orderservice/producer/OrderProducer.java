package com.fooddelivery.orderservice.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fooddelivery.orderservice.dto.NotificationEvent;

@Service
public class OrderProducer {

	@Autowired
	private KafkaTemplate<String,NotificationEvent> kafkaTemplate;
	
	public void sendNotification(NotificationEvent event) {
		System.out.println("Sending Event: " + event.getTitle());
		kafkaTemplate.send("order-topic",event);
	}
}
