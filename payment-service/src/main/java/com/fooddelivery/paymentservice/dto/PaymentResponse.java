package com.fooddelivery.paymentservice.dto;

import com.fooddelivery.paymentservice.entity.PaymentMethod;
import com.fooddelivery.paymentservice.entity.PaymentStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {

	private Long id;
	
	private Long orderId;
	
	private Double amount;
	
	private PaymentMethod paymentMethod;
	
	private PaymentStatus paymentStatus;

	public PaymentResponse() {
		super();
	}

	public PaymentResponse(Long id, Long orderId, Double amount, PaymentMethod paymentMethod,
			PaymentStatus paymentStatus) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
}
