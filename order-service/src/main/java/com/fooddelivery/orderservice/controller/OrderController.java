package com.fooddelivery.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Order APIs")
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	
	/*
	 * SERVICE : Order Service
	 * ACCESS  : CUSTOMER
	 * API     : POST /orders
	 * PURPOSE : Create a new order for a restaurant.
	 */
	@Operation(summary = "Create Order")
	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		System.out.println(auth.getName());
		System.out.println(auth.getAuthorities());
		return ResponseEntity.ok(orderService.createOrder(request));
	}

	
	/*
	 * SERVICE : Order Service
	 * ACCESS  : CUSTOMER, ADMIN
	 * API     : GET /orders/{id}
	 * PURPOSE : Retrieve order details by order ID.
	 */
	@Operation(summary = "Get Order by Id")
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.getOrderById(id));
	}

	
	/*
	 * SERVICE : Order Service
	 * ACCESS  : ADMIN
	 * API     : GET /orders
	 * PURPOSE : Retrieve all orders.
	 */
	@Operation(summary = "Get All Order")
	@GetMapping
	public ResponseEntity<List<OrderResponse>> getAllOrder() {
		return ResponseEntity.ok(orderService.getAllOrder());
	}

	
	/*
	 * SERVICE : Order Service
	 * ACCESS  : ADMIN
	 * API     : PUT /orders/{id}/status
	 * PURPOSE : Update order status manually.
	 */
	@Operation(summary = "Update Order Status by Id")
	@PutMapping("/{id}/status/{status}")
	public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @PathVariable String status) {
		return ResponseEntity.ok(orderService.updateStatus(status, id));
	}

	
	/*
	 * SERVICE : Order Service
	 * ACCESS  : CUSTOMER
	 * API     : DELETE /orders/{id}
	 * PURPOSE : Cancel an existing order.
	 */
	@Operation(summary = "Delete Order by Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.cancelOrder(id));
	}

	
	/*
	 * SERVICE : Order Service
	 * ACCESS  : RESTAURANT_OWNER
	 * API     : PUT /orders/{id}/accepted
	 * PURPOSE : Accept customer order.
	 */
	@Operation(summary = "Update OrderStatus(accepted) by Id")
	@PutMapping("/{id}/accepted")
	public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.acceptByRestaurant(id));
	}
	
	
	/*
	 * SERVICE : Order Service
	 * ACCESS  : RESTAURANT_OWNER
	 * API     : PUT /orders/{id}/accepted
	 * PURPOSE : Accept customer order.
	 */
	@Operation(summary = "Update OrderStatus(OutForDelivery) by Id")
	@PutMapping("/{id}/outdelivery")
	public ResponseEntity<OrderResponse> outForDelivery(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.outForDelivery(id));
	}

	
	/*
	 * SERVICE : Order Service
	 * ACCESS  : DELIVERY_PARTNER
	 * API     : PUT /orders/{id}/delivered
	 * PURPOSE : Mark order as Delivered.
	 */
	@PutMapping("/{id}/delivered")
	public ResponseEntity<OrderResponse> orderDelivered(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.orderDelivered(id));
	}

}
