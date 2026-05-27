package com.foodorder.system.model;

public class FoodOrder {

	private Integer orderId;
	private String customerName;
	private String foodName;
	private String status;
	public FoodOrder() {
		super();
	}
	public FoodOrder(Integer orderId, String customerName, String foodName, String status) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.foodName = foodName;
		this.status = status;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "FoodOrder [orderId=" + orderId + ", customerName=" + customerName + ", foodName=" + foodName
				+ ", status=" + status + "]";
	}
	
	

}
