package com.fooddelivery.orderservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderItemRequest {
	
	@NotNull(message = "Menu Item Id is required")
    private Long menuItemId;

//    @NotBlank(message = "Item name is required")
//    private String itemName;

    @NotNull(message = "Quantity is required")
    @Min(value = 1,
         message = "Quantity must be greater than 0")
    private Integer quantity;

//    @NotNull(message = "Price is required")
//    @Min(value = 1,
//         message = "Price must be greater than 0")
//    private BigDecimal price;

	public OrderItemRequest() {
		super();
	}

//	public OrderItemRequest(@NotNull(message = "Menu Item Id is required") Long menuItemId,
//			@NotBlank(message = "Item name is required") String itemName,
//			@NotNull(message = "Quantity is required") @Min(value = 1, message = "Quantity must be greater than 0") Integer quantity,
//			@NotNull(message = "Price is required") @Min(value = 1, message = "Price must be greater than 0") BigDecimal price) {
//		super();
//		this.menuItemId = menuItemId;
//		this.itemName = itemName;
//		this.quantity = quantity;
//		this.price = price;
//	}
	
	

	public OrderItemRequest(@NotNull(message = "Menu Item Id is required") Long menuItemId,
			@NotNull(message = "Quantity is required") @Min(value = 1, message = "Quantity must be greater than 0") Integer quantity) {
		super();
		this.menuItemId = menuItemId;
		this.quantity = quantity;
	}

	public Long getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}

	

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

//	public BigDecimal getPrice() {
//		return price;
//	}
//
//	public void setPrice(BigDecimal price) {
//		this.price = price;
//	}
//	
//	public String getItemName() {
//		return itemName;
//	}
//
//	public void setItemName(String itemName) {
//		this.itemName = itemName;
//	}
    
    
}
