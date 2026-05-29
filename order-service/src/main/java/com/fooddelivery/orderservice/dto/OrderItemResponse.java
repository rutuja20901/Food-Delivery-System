package com.fooddelivery.orderservice.dto;

import java.math.BigDecimal;

public class OrderItemResponse {
	private Long id;

    private Long menuItemId;

    private String itemName;

    private Integer quantity;

    private BigDecimal price;
    
    private BigDecimal subTotal;

	public OrderItemResponse() {
		super();
	}

	public OrderItemResponse(Long id, Long menuItemId, String itemName, Integer quantity, BigDecimal price) {
		super();
		this.id = id;
		this.menuItemId = menuItemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}
	
	

	public OrderItemResponse(Long id, Long menuItemId, String itemName, Integer quantity, BigDecimal price,
			BigDecimal subTotal) {
		super();
		this.id = id;
		this.menuItemId = menuItemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.subTotal = subTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	
	
    
    
}
