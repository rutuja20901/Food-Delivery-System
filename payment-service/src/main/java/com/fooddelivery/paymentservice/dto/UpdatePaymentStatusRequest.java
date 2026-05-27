package com.fooddelivery.paymentservice.dto;

import com.fooddelivery.paymentservice.entity.PaymentStatus;

public class UpdatePaymentStatusRequest {

	private PaymentStatus paymentStatus;

	public UpdatePaymentStatusRequest() {
		super();
	}

	
	public UpdatePaymentStatusRequest(PaymentStatus paymentStatus) {
		super();
		this.paymentStatus = paymentStatus;
	}



	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
}
