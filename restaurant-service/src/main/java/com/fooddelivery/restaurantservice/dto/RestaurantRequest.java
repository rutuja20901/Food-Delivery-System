package com.fooddelivery.restaurantservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RestaurantRequest {

	
	@NotBlank(message="Restaurant name required")
	private String name;
	
	@NotBlank(message="Address required")
	private String address;
	
	@NotBlank(message="Cuisine Type required")
	private String cuisineType;
	
	@NotNull
	private boolean open;
	
	@NotBlank(message="Owner Name required")
	private String ownerName;

	public RestaurantRequest() {
		super();
	}

	public RestaurantRequest(@NotBlank(message = "Restaurant name required") String name,
			@NotBlank(message = "Address required") String address,
			@NotBlank(message = "Cuisine Type required") String cuisineType, boolean open) {
		super();
		this.name = name;
		this.address = address;
		this.cuisineType = cuisineType;
		this.open = open;
	}
	
	

	

	public RestaurantRequest(@NotBlank(message = "Restaurant name required") String name,
			@NotBlank(message = "Address required") String address,
			@NotBlank(message = "Cuisine Type required") String cuisineType, @NotNull boolean open,
			@NotBlank(message = "Owner Name required") String ownerName) {
		super();
		this.name = name;
		this.address = address;
		this.cuisineType = cuisineType;
		this.open = open;
		this.ownerName = ownerName;
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
	
	

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Override
	public String toString() {
		return "RestaurantRequest [name=" + name + ", address=" + address + ", cuisineType=" + cuisineType + ", open="
				+ open + "]";
	}
	
	
}
