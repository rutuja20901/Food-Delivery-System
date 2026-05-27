package com.fooddelivery.restaurantservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MenuRequest {

	@NotBlank(message="Item name required")
	private String itemName;
	
	@NotBlank(message="Price required")
	private double price;
	
	private boolean available;
	
	@NotBlank(message="Restaurant Id required")
	private Long restaurantId;

	public MenuRequest() {
		super();
	}

	public MenuRequest(@NotBlank(message = "Item name required") String itemName,
			@NotBlank(message = "Price required") double price, boolean available,
			@NotBlank(message = "Restaurant Id required") Long restaurantId) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.available = available;
		this.restaurantId = restaurantId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public String toString() {
		return "MenuRequest [itemName=" + itemName + ", price=" + price + ", available=" + available + ", restaurantId="
				+ restaurantId + "]";
	}
	
	
}
