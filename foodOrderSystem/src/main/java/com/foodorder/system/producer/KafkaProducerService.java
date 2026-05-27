package com.foodorder.system.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.foodorder.system.model.FoodOrder;

@Service
public class KafkaProducerService {
	private static final String TOPIC = "food-order-topic";
	
	@Autowired
	private KafkaTemplate<String,FoodOrder> kafkaTemplate;
	
	public void sendorder(FoodOrder event) {
		kafkaTemplate.send(TOPIC, event);
		System.out.println("Order Published. " + event);
		
	}
}
