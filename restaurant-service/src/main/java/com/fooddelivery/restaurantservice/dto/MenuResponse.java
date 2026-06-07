package com.fooddelivery.restaurantservice.dto;

import lombok.Data;

@Data
public class MenuResponse {

	private Long id;
	
	private String itemName;
	
	private double price;
	
	private boolean open;
	
	private String restaurantName;
	
	private Long restaurantId;

	public MenuResponse() {
		super();
	}

	public MenuResponse(Long id, String itemName, double price, boolean open, String restaurantName) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.open = open;
		this.restaurantName = restaurantName;
	}
	
	

	public MenuResponse(Long id, String itemName, double price, boolean open, String restaurantName,
			Long restaurantId) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.open = open;
		this.restaurantName = restaurantName;
		this.restaurantId = restaurantId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	
	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public String toString() {
		return "MenuResponse [id=" + id + ", itemName=" + itemName + ", price=" + price + ", open=" + open
				+ ", restaurantName=" + restaurantName + "]";
	}
	
	
}
