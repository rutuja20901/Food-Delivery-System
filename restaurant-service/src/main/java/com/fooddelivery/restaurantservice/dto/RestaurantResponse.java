package com.fooddelivery.restaurantservice.dto;

import lombok.Data;

@Data
public class RestaurantResponse {

	private Long id;
	
	private String name;
	
	private String address;
	
	private String cuisineType;
	
	private boolean open;
	
	

	public RestaurantResponse() {
		super();
	}

	public RestaurantResponse(Long id, String name, String address, String cuisineType, boolean open) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.cuisineType = cuisineType;
		this.open = open;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return "RestaurantResponse [id=" + id + ", name=" + name + ", address=" + address + ", cuisineType="
				+ cuisineType + ", open=" + open + "]";
	}
	
	
	
}
