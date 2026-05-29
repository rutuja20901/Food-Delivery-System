package com.fooddelivery.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.orderservice.dto.OrderRequest;
import com.fooddelivery.orderservice.dto.OrderResponse;
import com.fooddelivery.orderservice.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	

	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
		
		return ResponseEntity.ok(orderService.createOrder(request));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id){
		return ResponseEntity.ok(orderService.getOrderById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<OrderResponse>> getAllOrder(){
		return ResponseEntity.ok(orderService.getAllOrder());
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id,@PathVariable String status){
		return ResponseEntity.ok(orderService.updateStatus(status, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> cancelOrder(@PathVariable Long id){
		return ResponseEntity.ok(orderService.cancelOrder(id));
	}
}
