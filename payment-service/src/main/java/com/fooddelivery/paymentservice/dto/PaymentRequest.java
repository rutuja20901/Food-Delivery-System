package com.fooddelivery.paymentservice.dto;

import com.fooddelivery.paymentservice.entity.PaymentMethod;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {

	@NotNull(message="Order id is required")
	private Long orderId;
	
	@NotNull(message="Amount is required")
	private Double amount;
	
	@NotNull(message="Payment method is required")
	private PaymentMethod paymentMethod;

	public PaymentRequest() {
		super();
	}

	public PaymentRequest(@NotBlank(message = "Order id is required") Long orderId,
			@NotBlank(message = "Amount is required") Double amount,
			@NotBlank(message = "Payment method is required") PaymentMethod paymentMethod) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
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

	
	
	
	
}
