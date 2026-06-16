package com.fooddelivery.restaurantservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.restaurantservice.dto.RestaurantRequest;
import com.fooddelivery.restaurantservice.dto.RestaurantResponse;
import com.fooddelivery.restaurantservice.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Tag(name="Restaurant APIs")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	/*
	 * SERVICE : Restaurant Service
	 * ACCESS  : RESTAURANT_OWNER
	 * API     : Create Restaurant
	 * PURPOSE : Create New Restaurant.
	 */
	@Operation(summary="Add Restaurant")
	@PostMapping
	public RestaurantResponse addRestaurant(@Valid @RequestBody RestaurantRequest res) {
		return restaurantService.addRestaurant(res);
	}
	
	
	/*
	 * SERVICE : Restaurant Service
	 * ACCESS  : ADMIN
	 * API     : Approve Restaurant
	 * PURPOSE : Approve restaurant registration.
	 */
	@Operation(summary="Update Restaurant Status Approve by Id")
	@PatchMapping("/{id}/approve")
	public ResponseEntity<RestaurantResponse> approveRestaurant(@PathVariable Long id){
		return ResponseEntity.ok(restaurantService.approveRestaurant(id));
	}
	
	
	/*
	 * SERVICE : Restaurant Service
	 * ACCESS  : ADMIN
	 * API     : Reject Restaurant
	 * PURPOSE : Reject restaurant registration.
	 */
	@Operation(summary="Update Restaurant Status Reject by Id")
	@PatchMapping("/{id}/reject")
	public ResponseEntity<RestaurantResponse> rejectRestaurant(@PathVariable Long id){
		return ResponseEntity.ok(restaurantService.rejectRestaurant(id));
	}
	
	/*
	 * SERVICE : Restaurant Service
	 * ACCESS  : CUSTOMER, ADMIN, RESTAURANT_OWNER
	 * API     : GET /restaurants
	 * PURPOSE : View all approved restaurants
	 */
	@Operation(summary="Get All Restaurant")
	@GetMapping
	public List<RestaurantResponse> getRestaurant(){
		return restaurantService.getRestaurant();
	}
	
	/*
	 * SERVICE : Restaurant Service
	 * ACCESS  : CUSTOMER, ADMIN, RESTAURANT_OWNER
	 * API     : GET /restaurants/{id}
	 * PURPOSE : View restaurant details
	 */
	@Operation(summary="Get Restaurant by Id")
	@GetMapping("/{id}")
	public RestaurantResponse getRestaurantById(@PathVariable Long id) {
		return restaurantService.getRestaurantById(id);
	}
	
	/* SERVICE : Restaurant Service
	 * ACCESS  : RESTAURANT_OWNER, ADMIN
	 * API     : PUT /restaurants/{id}
	 * PURPOSE : Update restaurant details
	 */
	@Operation(summary="Update Restaurant by Id")
	@PutMapping("/{id}")
	public RestaurantResponse updateRestaurant(@Valid @RequestBody RestaurantRequest res, @PathVariable Long id) {
		return restaurantService.updateRestaurant(id, res);
	}
	
	/* SERVICE : Restaurant Service
	 * ACCESS  : ADMIN
	 * API     : DELETE /restaurants/{id}
	 * PURPOSE : Remove restaurant
	 */
	@Operation(summary="Delete Restaurant by Id")
	@DeleteMapping("/{id}")
	public String deleteRestaurant(@PathVariable Long id) {
		return restaurantService.deleteRestaurant(id);
	}
	
	
	/*
	 * SERVICE : Restaurant Service
	 * ACCESS  : CUSTOMER, ADMIN, RESTAURANT_OWNER
	 * API     : GET /restaurants/{id}
	 * PURPOSE : Internal api for get restaurant 
	 */
	@Operation(summary="Internal Api - Get Restaurant by Id")
	@GetMapping("/internal/{id}")
	public RestaurantResponse getRestaurant(@PathVariable Long id) {
		
		return restaurantService.getRestaurantById(id);
	}
}
