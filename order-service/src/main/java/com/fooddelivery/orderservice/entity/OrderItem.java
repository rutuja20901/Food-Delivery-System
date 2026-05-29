package com.fooddelivery.orderservice.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long menuItemId;
	
	private String itemName;
	
	private Integer quantity;
	
	private BigDecimal price;
	
	private BigDecimal subTotal;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;

	public OrderItem() {
		super();
	}

	public OrderItem(Long id, Long menuItemId, String itemName, Integer quantity, BigDecimal price, BigDecimal subTotal,
			Order order) {
		super();
		this.id = id;
		this.menuItemId = menuItemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.subTotal = subTotal;
		this.order = order;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
	
	
}
