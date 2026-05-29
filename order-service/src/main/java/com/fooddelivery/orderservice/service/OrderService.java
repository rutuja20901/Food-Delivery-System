package com.fooddelivery.orderservice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.orderservice.dto.OrderItemRequest;
import com.fooddelivery.orderservice.dto.OrderItemResponse;
import com.fooddelivery.orderservice.dto.OrderRequest;
import com.fooddelivery.orderservice.dto.OrderResponse;
import com.fooddelivery.orderservice.entity.Order;
import com.fooddelivery.orderservice.entity.OrderItem;
import com.fooddelivery.orderservice.enums.OrderStatus;
import com.fooddelivery.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public OrderResponse createOrder(OrderRequest request) {
		Order order = new Order();
		
		order.setUserId(request.getUserId());
		order.setRestaurantId(request.getRestaurantId());
		order.setOrderStatus(OrderStatus.PLACED);
		order.setCreatedAt(LocalDateTime.now());
		
		List<OrderItem> orderItem = new ArrayList<>();
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		for(OrderItemRequest itemRequest: request.getItems()) {
			OrderItem item = new OrderItem();
			
			BigDecimal subTotal =
	                itemRequest.getPrice().multiply(
	                        BigDecimal.valueOf(
	                                itemRequest.getQuantity()
	                        ));
			
			item.setMenuItemId(itemRequest.getMenuItemId());
			item.setItemName(itemRequest.getItemName());
			item.setQuantity(itemRequest.getQuantity());
			item.setPrice(itemRequest.getPrice());
			item.setSubTotal(subTotal);
			
			item.setOrder(order);
			orderItem.add(item);
			
			totalAmount = totalAmount.add(subTotal);
			
		}
		
		
		   order.setItems(orderItem);
		    order.setAmount(totalAmount);

		    Order savedOrder = orderRepository.save(order);

		    return mapToResponse(savedOrder);
		
		
		
		
	}
	
	public OrderResponse getOrderById(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));
		return mapToResponse(order);
	}
	
	public List<OrderResponse> getAllOrder(){
		return orderRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}
	
	
	public OrderResponse updateStatus(String status, Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));
		 order.setOrderStatus(
		            OrderStatus.valueOf(status.toUpperCase()));
		return mapToResponse(order);
	}
	
	public String cancelOrder(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));
		order.setOrderStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);
		return "Order Deleted Successfully";
	}
	
	public OrderResponse mapToResponse(Order order) {
		
		
		List<OrderItemResponse> itemResponse = new ArrayList<>();
		for(OrderItem item:order.getItems()) {
			OrderItemResponse orderItemResponse = new OrderItemResponse();
			orderItemResponse.setId(item.getId());
			orderItemResponse.setItemName(item.getItemName());
			orderItemResponse.setSubTotal(item.getSubTotal());
			orderItemResponse.setMenuItemId(item.getMenuItemId());
			orderItemResponse.setPrice(item.getPrice());
			orderItemResponse.setQuantity(item.getQuantity());
			
			itemResponse.add(orderItemResponse);
			
		}
		
		
		OrderResponse response  = new OrderResponse();
		response.setId(order.getId());
		response.setUserId(order.getUserId());
		response.setRestaurantId(order.getRestaurantId());
		response.setOrderStatus(order.getOrderStatus());
		response.setCreatedAt(order.getCreatedAt());
		response.setTotalAmount(order.getAmount());
		
		response.setItems(itemResponse);
		return response;
	}
}
