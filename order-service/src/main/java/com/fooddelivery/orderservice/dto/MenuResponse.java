package com.fooddelivery.orderservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MenuResponse {

	private Long id;
	private String itemName;
	private BigDecimal price;
	private Long restaurantId;
	private Boolean available;
	public MenuResponse() {
		super();
	}
	public MenuResponse(Long id, String itemName, BigDecimal price, Long restaurantId, Boolean available) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.restaurantId = restaurantId;
		this.available = available;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	
	
}
