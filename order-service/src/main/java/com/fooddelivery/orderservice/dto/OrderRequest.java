package com.fooddelivery.orderservice.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrderRequest {

	  	@NotNull(message = "User Id is required")
	    private Long userId;

	    @NotNull(message = "Restaurant Id is required")
	    private Long restaurantId;

	    @Valid
	    @NotEmpty(message = "Order items cannot be empty")
	    private List<OrderItemRequest> items;

		public OrderRequest() {
			super();
		}

		public OrderRequest(@NotNull(message = "User Id is required") Long userId,
				@NotNull(message = "Restaurant Id is required") Long restaurantId,
				@Valid @NotEmpty(message = "Order items cannot be empty") List<OrderItemRequest> items) {
			super();
			this.userId = userId;
			this.restaurantId = restaurantId;
			this.items = items;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getRestaurantId() {
			return restaurantId;
		}

		public void setRestaurantId(Long restaurantId) {
			this.restaurantId = restaurantId;
		}

		public List<OrderItemRequest> getItems() {
			return items;
		}

		public void setItems(List<OrderItemRequest> items) {
			this.items = items;
		}
	    
	    
	    
}
