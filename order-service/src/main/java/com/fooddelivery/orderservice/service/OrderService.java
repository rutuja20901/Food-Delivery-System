package com.fooddelivery.orderservice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fooddelivery.orderservice.dto.MenuResponse;
import com.fooddelivery.orderservice.dto.NotificationEvent;
import com.fooddelivery.orderservice.dto.OrderItemRequest;
import com.fooddelivery.orderservice.dto.OrderItemResponse;
import com.fooddelivery.orderservice.dto.OrderRequest;
import com.fooddelivery.orderservice.dto.OrderResponse;
import com.fooddelivery.orderservice.dto.RestaurantResponse;
import com.fooddelivery.orderservice.entity.Order;
import com.fooddelivery.orderservice.entity.OrderItem;
import com.fooddelivery.orderservice.enums.OrderStatus;
import com.fooddelivery.orderservice.exception.ResourceNotFoundException;
import com.fooddelivery.orderservice.producer.OrderProducer;
import com.fooddelivery.orderservice.repository.OrderRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class OrderService {
	
	private static final Logger log =
	        LoggerFactory.getLogger(
	           OrderService.class);


	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private OrderProducer producer;
	
	public OrderResponse createOrder(OrderRequest request) {
		String authHeader = httpServletRequest.getHeader("Authorization");

		System.out.println("Incoming Auth Header: " + authHeader);
		String url = "http://localhost:8082/restaurant/internal/" + request.getRestaurantId();
		
//		RestaurantResponse res = restTemplate.getForObject(url, RestaurantResponse.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authHeader);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<RestaurantResponse> response =
		        restTemplate.exchange(
		                url,
		                HttpMethod.GET,
		                entity,
		                RestaurantResponse.class
		        );

		RestaurantResponse res = response.getBody();
		
		if(res == null) {
			throw new ResourceNotFoundException("Restaurant not found!");
		}
		
		
		Order order = new Order();
		
		order.setUserId(request.getUserId());
		order.setRestaurantId(request.getRestaurantId());
		order.setOrderStatus(OrderStatus.PLACED);
		order.setCreatedAt(LocalDateTime.now());
		
		List<OrderItem> orderItem = new ArrayList<>();
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		for(OrderItemRequest itemRequest: request.getItems()) {
			OrderItem item = new OrderItem();
			String authHeader1 = httpServletRequest.getHeader("Authorization");

			System.out.println("Incoming Auth Header: " + authHeader1);
			String menuUrl = "http://localhost:8082/menu/" + itemRequest.getMenuItemId(); 
			
//			MenuResponse menuResponse = restTemplate.getForObject(menuUrl, MenuResponse.class);
			HttpEntity<String> entity1 = new HttpEntity<>(headers);

			ResponseEntity<MenuResponse> response1 =
			        restTemplate.exchange(
			        		menuUrl,
			                HttpMethod.GET,
			                entity1,
			                MenuResponse.class
			        );

			MenuResponse res1 = response1.getBody();
			
			if(res1 == null) {
				throw new ResourceNotFoundException("Menu not found!");
			}
			
			if(!res1.getRestaurantId().equals(request.getRestaurantId())) {
				throw new ResourceNotFoundException("Menu doesn't belong to restaurant");
			}
			
			BigDecimal subTotal =
					res1.getPrice().multiply(
	                        BigDecimal.valueOf(
	                                itemRequest.getQuantity()
	                        ));
			
			item.setMenuItemId(res1.getId());
			item.setItemName(res1.getItemName());
			item.setQuantity(itemRequest.getQuantity());
			item.setPrice(res1.getPrice());
			item.setSubTotal(subTotal);
			
			item.setOrder(order);
			orderItem.add(item);
			
			totalAmount = totalAmount.add(subTotal);
			
		}
		
		
		   order.setItems(orderItem);
		    order.setAmount(totalAmount);

		    Order savedOrder = orderRepository.save(order);

		    
		    NotificationEvent event = new NotificationEvent(
		    		savedOrder.getUserId(),
		    		savedOrder.getId(),
	        		"Order Placed",
	        		"Your order has been placed successfully.");
	        
	        producer.sendNotification(event);
		    return mapToResponse(savedOrder);
		
		
		
		
	}
	
	public OrderResponse getOrderById(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
		return mapToResponse(order);
	}
	
	public List<OrderResponse> getAllOrder(){
		return orderRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}
	
	
	public OrderResponse updateStatus(String status, Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
		 order.setOrderStatus(
		            OrderStatus.valueOf(status.toUpperCase()));
		return mapToResponse(order);
	}
	
	public String cancelOrder(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
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
