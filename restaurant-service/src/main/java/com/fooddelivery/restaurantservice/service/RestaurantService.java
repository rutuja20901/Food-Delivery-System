package com.fooddelivery.restaurantservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fooddelivery.restaurantservice.dto.RestaurantRequest;
import com.fooddelivery.restaurantservice.dto.RestaurantResponse;
import com.fooddelivery.restaurantservice.entity.Restaurant;
import com.fooddelivery.restaurantservice.exception.ResourceNotFoundException;
import com.fooddelivery.restaurantservice.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepo;
	
	/*
	 * BUSINESS LOGIC :
	 * Create new restaurant.
	 * Set approval status -> PENDING
	 * Assign restaurant owner.
	 * Save restaurant details.
	 */
	public RestaurantResponse addRestaurant(RestaurantRequest restaurant) {
		Restaurant res = new Restaurant();
		res.setName(restaurant.getName());
		res.setAddress(restaurant.getAddress());
		res.setCuisineType(restaurant.getCuisineType());
		res.setOpen(restaurant.isOpen());
		res.setApprovalStatus("PENDING");
		res.setOwnerName(restaurant.getOwnerName());
		Restaurant saved = restaurantRepo.save(res);
		
		//Response for create Restaurant
		RestaurantResponse response = new RestaurantResponse();
		
		response.setName(saved.getName());
		response.setAddress(saved.getAddress());
		response.setCuisineType(saved.getCuisineType());
		response.setOpen(saved.isOpen());
		
		
		return response;
	}
	
	
	/*
	 * BUSINESS LOGIC :
	 * PENDING -> APPROVED
	 * Validate restaurant existence
	 * Update approval status
	 */
	public RestaurantResponse approveRestaurant(Long id) {
		Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
		restaurant.setApprovalStatus("APPROVED");
		restaurantRepo.save(restaurant);
		return mapToResponse(restaurant);
	}
	
	
	/*
	 * BUSINESS LOGIC :
	 * Fetch all approved restaurants.
	 * Validate available records.
	 * Map entity -> response DTO.
	 * Return restaurant list.
	 */
	public List<RestaurantResponse> getRestaurant(){
		return restaurantRepo.findAll()
				.stream()
				.map(this::mapToResponse)
				.toList();
	}
	
	
	/*
	 * BUSINESS LOGIC :
	 * Validate restaurant existence.
	 * Fetch restaurant by ID.
	 * Map entity -> response DTO.
	 * Return restaurant details.
	 */
	public RestaurantResponse getRestaurantById(Long id) {
		Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Restaurant not found!"));
		return mapToResponse(restaurant);
	}
	
	
	/*
	 * BUSINESS LOGIC :
	 * Validate restaurant existence.
	 * Verify restaurant ownership.
	 * Update restaurant details.
	 * Save updated restaurant.
	 */
	public RestaurantResponse updateRestaurant(Long id, RestaurantRequest restaurant) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId = authentication.getName();
		
		Restaurant rest =
                restaurantRepo.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                       "Restaurant Not Found"));

		if(rest.getOwnerName().equals(loggedInUserId)) {
			throw new ResourceNotFoundException("Unauthorized Access!");
		}
		
		
		RestaurantResponse res = getRestaurantById(id);
		
		res.setName(restaurant.getName());
		res.setAddress(restaurant.getAddress());
		res.setCuisineType(restaurant.getCuisineType());
		res.setOpen(restaurant.isOpen());
		
		return res;
	}
	
	
	/*
	 * BUSINESS LOGIC :
	 * Validate restaurant existence.
	 * Verify delete authorization.
	 * Remove restaurant record.
	 * Return deletion confirmation.
	 */
	public String deleteRestaurant(Long id) {
		 Restaurant menu =
	                restaurantRepo.findById(id)
	                .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                       "Restaurant Not Found"));

	        restaurantRepo.delete(menu);

	        return "Restaurant Deleted";
	}
	
	 private RestaurantResponse
	    mapToResponse(
	            Restaurant restaurant){

	        RestaurantResponse response =
	                new RestaurantResponse();

	        response.setId(restaurant.getId());
	        response.setName(restaurant.getName());
	        response.setAddress(
	                restaurant.getAddress());

	        response.setCuisineType(
	                restaurant.getCuisineType());

	        response.setOpen(
	                restaurant.isOpen());

	        return response;
	 }
	
}
