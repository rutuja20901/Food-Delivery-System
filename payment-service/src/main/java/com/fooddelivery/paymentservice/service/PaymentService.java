package com.fooddelivery.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.paymentservice.dto.PaymentRequest;
import com.fooddelivery.paymentservice.dto.PaymentResponse;
import com.fooddelivery.paymentservice.dto.UpdatePaymentStatusRequest;
import com.fooddelivery.paymentservice.entity.Payment;
import com.fooddelivery.paymentservice.entity.PaymentStatus;
import com.fooddelivery.paymentservice.exception.ResourceNotFoundException;
import com.fooddelivery.paymentservice.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public PaymentResponse createPayment(PaymentRequest request) {
		Payment payment = new Payment();
		
		payment.setOrderId(request.getOrderId());
		payment.setAmount(request.getAmount());
		payment.setPaymentMethod(request.getPaymentMethod());
		payment.setPaymentStatus(PaymentStatus.SUCCESS);
		
		Payment saved = paymentRepository.save(payment);
		
		return mapToResponse(saved);
	}
	
	
	//Get payment by id
	public PaymentResponse getPaymentById(Long id) {
		Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment not found!"));
		
		return mapToResponse(payment);
	}
	
	// Get payment by order id
	public PaymentResponse getPaymentByOrderId(Long orderId) {
		Payment payment = paymentRepository.findByOrderId(orderId).orElseThrow(()-> new ResourceNotFoundException("Order payment not found"));
		return mapToResponse(payment);
	}
	
	//Update payment status
	public PaymentResponse updatePayementStatus(Long id, UpdatePaymentStatusRequest update) {
		Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("payment not found!"));
		
		payment.setPaymentStatus(update.getPaymentStatus());
		Payment updated = paymentRepository.save(payment);
		return mapToResponse(updated);
	
	}
	
	//Delete payment
	public String deletePayment(Long id) {
		Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment not found!"));
		paymentRepository.delete(payment);
		return "Payment Deleted Successfully";
	}
	
	private PaymentResponse mapToResponse(Payment request) {
		PaymentResponse response  = new PaymentResponse();
		
		response.setId(request.getId());
		response.setOrderId(request.getOrderId());
		response.setAmount(request.getAmount());
		response.setPaymentMethod(request.getPaymentMethod());
		response.setPaymentStatus(request.getPaymentStatus());
		
		return response;
	}
}
