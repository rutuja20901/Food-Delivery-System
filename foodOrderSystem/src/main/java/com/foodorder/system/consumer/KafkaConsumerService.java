package com.foodorder.system.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.foodorder.system.model.FoodOrder;

@Service
public class KafkaConsumerService {
	@KafkaListener(topics="food-order-topic",groupId ="notofication-group")
	public void consume(FoodOrder event) {
		System.out.println("=======================");
		System.out.println("Notification Received");
		System.out.println("Order ID : " + event.getOrderId());
		System.out.println("Customer : " + event.getCustomerName());
		System.out.println("Status : " + event.getStatus());
		System.out.println("=======================");
		
	}
}
