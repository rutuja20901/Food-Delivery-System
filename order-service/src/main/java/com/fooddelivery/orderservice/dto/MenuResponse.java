package com.fooddelivery.orderservice.dto;

import lombok.Data;

@Data
public class MenuResponse {

	private Long id;
	private String itemName;
	private Double price;
	public MenuResponse() {
		super();
	}
	public MenuResponse(Long id, String itemName, Double price) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.price = price;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
