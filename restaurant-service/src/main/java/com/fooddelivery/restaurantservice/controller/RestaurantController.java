package com.fooddelivery.restaurantservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.restaurantservice.dto.RestaurantRequest;
import com.fooddelivery.restaurantservice.dto.RestaurantResponse;
import com.fooddelivery.restaurantservice.entity.Restaurant;
import com.fooddelivery.restaurantservice.service.RestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping
	public RestaurantResponse addRestaurant(@Valid @RequestBody RestaurantRequest res) {
		return restaurantService.addRestaurant(res);
	}
	
	@GetMapping
	public List<RestaurantResponse> getRestaurant(){
		return restaurantService.getRestaurant();
	}
	
	@GetMapping("/{id}")
	public RestaurantResponse getRestaurantById(@PathVariable Long id) {
		return restaurantService.getRestaurantById(id);
	}
	
	@PutMapping("/{id}")
	public RestaurantResponse updateRestaurant(@Valid @RequestBody RestaurantRequest res, @PathVariable Long id) {
		return restaurantService.updateRestaurant(id, res);
	}
	
	@DeleteMapping("/{id}")
	public String deleteRestaurant(@PathVariable Long id) {
		return restaurantService.deleteRestaurant(id);
	}
}
