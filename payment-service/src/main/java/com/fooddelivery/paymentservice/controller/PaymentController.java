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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest request) {
		return ResponseEntity.ok(paymentService.createPayment(request));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id){
		return ResponseEntity.ok(paymentService.getPaymentById(id));
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<PaymentResponse> getPaymentOrderId(@PathVariable Long orderId){
		return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Long id, @RequestBody UpdatePaymentStatusRequest request){
		return ResponseEntity.ok(paymentService.updatePayementStatus(id, request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePayment(@PathVariable Long id){
		return ResponseEntity.ok(paymentService.deletePayment(id));
	}
}
