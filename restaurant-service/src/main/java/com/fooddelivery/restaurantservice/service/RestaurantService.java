package com.fooddelivery.restaurantservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	//Add restaurant 
	public RestaurantResponse addRestaurant(RestaurantRequest restaurant) {
		Restaurant res = new Restaurant();
		res.setName(restaurant.getName());
		res.setAddress(restaurant.getAddress());
		res.setCuisineType(restaurant.getCuisineType());
		res.setOpen(restaurant.isOpen());
		Restaurant saved = restaurantRepo.save(res);
		
		RestaurantResponse response = new RestaurantResponse();
		
		response.setName(saved.getName());
		response.setAddress(saved.getAddress());
		response.setCuisineType(saved.getCuisineType());
		response.setOpen(saved.isOpen());
		
		return response;
	}
	
	//Get all details
	public List<RestaurantResponse> getRestaurant(){
		return restaurantRepo.findAll()
				.stream()
				.map(this::mapToResponse)
				.toList();
	}
	
	// Get restaurant by id
	public RestaurantResponse getRestaurantById(Long id) {
		Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Restaurant not found!"));
		return mapToResponse(restaurant);
	}
	
	// Update restaurant by id
	public RestaurantResponse updateRestaurant(Long id, RestaurantRequest restaurant) {
		RestaurantResponse res = getRestaurantById(id);
		
		res.setName(restaurant.getName());
		res.setAddress(restaurant.getAddress());
		res.setCuisineType(restaurant.getCuisineType());
		res.setOpen(restaurant.isOpen());
		
		return res;
	}
	
	// Delete Restaurant
	
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
