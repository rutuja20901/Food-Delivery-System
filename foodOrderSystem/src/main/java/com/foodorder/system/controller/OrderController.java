package com.foodorder.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodorder.system.model.FoodOrder;
import com.foodorder.system.producer.KafkaProducerService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private KafkaProducerService producerService;
	
	@PostMapping("/publish")
	public String  publishOrder(@RequestBody FoodOrder event) {
		producerService.sendorder(event);
		return "Order sent successfully";
		
	}
}
