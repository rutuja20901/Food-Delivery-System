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

	
	/*
	 * BUSINESS LOGIC :
	 * Create payment entity.
	 * Assign order details.
	 * Set payment method and amount.
	 * Mark payment status.
	 * Save payment into database.
	 * Return payment response.
	 */
	public PaymentResponse createPayment(PaymentRequest request) {
		Payment payment = new Payment();

		payment.setOrderId(request.getOrderId());
		payment.setAmount(request.getAmount());
		payment.setPaymentMethod(request.getPaymentMethod());
		payment.setPaymentStatus(PaymentStatus.SUCCESS);

		Payment saved = paymentRepository.save(payment);

		return mapToResponse(saved);
	}

	/*
	 * BUSINESS LOGIC :
	 * Create payment entity.
	 * Assign order details.
	 * Set payment method and amount.
	 * Mark payment status.
	 * Save payment into database.
	 * Return payment response.
	 */
	public PaymentResponse getPaymentById(Long id) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found!"));

		return mapToResponse(payment);
	}

	
	/*
	 * BUSINESS LOGIC :
	 * Find payment using order ID.
	 * Validate payment existence.
	 * Convert entity to response DTO.
	 * Return payment details.
	 */
	public PaymentResponse getPaymentByOrderId(Long orderId) {
		Payment payment = paymentRepository.findByOrderId(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order payment not found"));
		return mapToResponse(payment);
	}

	
	/*
	 * BUSINESS LOGIC :
	 * Find payment by ID.
	 * Validate payment existence.
	 * Update payment status.
	 * Save updated payment.
	 * Return updated payment response.
	 */
	public PaymentResponse updatePayementStatus(Long id, UpdatePaymentStatusRequest update) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("payment not found!"));

		payment.setPaymentStatus(update.getPaymentStatus());
		Payment updated = paymentRepository.save(payment);
		return mapToResponse(updated);

	}

	
	/*
	 * BUSINESS LOGIC :
	 * Find payment by ID.
	 * Validate payment existence.
	 * Delete payment record.
	 * Return success message.
	 */
	public String deletePayment(Long id) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found!"));
		paymentRepository.delete(payment);
		return "Payment Deleted Successfully";
	}

	
	/*
	 * BUSINESS LOGIC :
	 * Convert Payment entity to PaymentResponse DTO.
	 * Map payment details.
	 * Return formatted response.
	 */
	private PaymentResponse mapToResponse(Payment request) {
		PaymentResponse response = new PaymentResponse();

		response.setId(request.getId());
		response.setOrderId(request.getOrderId());
		response.setAmount(request.getAmount());
		response.setPaymentMethod(request.getPaymentMethod());
		response.setPaymentStatus(request.getPaymentStatus());

		return response;
	}
}
