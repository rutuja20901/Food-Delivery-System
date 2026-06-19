package com.fooddelivery.paymentservice.controller;

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

import com.fooddelivery.paymentservice.dto.PaymentRequest;
import com.fooddelivery.paymentservice.dto.PaymentResponse;
import com.fooddelivery.paymentservice.dto.UpdatePaymentStatusRequest;
import com.fooddelivery.paymentservice.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Payment APIs")
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	
	/*
	 * SERVICE : Payment Service
	 * ACCESS  : CUSTOMER
	 * API     : POST /payment
	 * PURPOSE : Create payment for an order.
	 */
	@Operation(summary = "Create Payment Request")
	@PostMapping
	public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest request) {
		return ResponseEntity.ok(paymentService.createPayment(request));
	}

	
	/*
	 * SERVICE : Payment Service
	 * ACCESS  : CUSTOMER, ADMIN
	 * API     : GET /payment/{id}
	 * PURPOSE : Retrieve payment details by payment ID.
	 */
	@Operation(summary = "Get Payment by Id")
	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id) {
		return ResponseEntity.ok(paymentService.getPaymentById(id));
	}

	
	/*
	 * SERVICE : Payment Service
	 * ACCESS  : CUSTOMER, ADMIN
	 * API     : GET /payment/order/{id}
	 * PURPOSE : Retrieve payment details using order ID.
	 */
	@Operation(summary = "Get Payment by Order Id")
	@GetMapping("/order/{orderId}")
	public ResponseEntity<PaymentResponse> getPaymentOrderId(@PathVariable Long orderId) {
		return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
	}

	
	/*
	 * SERVICE : Payment Service
	 * ACCESS  : ADMIN
	 * API     : PUT /payment/{id}
	 * PURPOSE : Update payment status.
	 */
	@Operation(summary = "Update Payment by Id")
	@PutMapping("/{id}")
	public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Long id,
			@RequestBody UpdatePaymentStatusRequest request) {
		return ResponseEntity.ok(paymentService.updatePayementStatus(id, request));
	}

	
	/*
	 * SERVICE : Payment Service
	 * ACCESS  : ADMIN
	 * API     : DELETE /payment/{id}
	 * PURPOSE : Delete payment record.
	 */
	@Operation(summary = "Delete Payment")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePayment(@PathVariable Long id) {
		return ResponseEntity.ok(paymentService.deletePayment(id));
	}
}
