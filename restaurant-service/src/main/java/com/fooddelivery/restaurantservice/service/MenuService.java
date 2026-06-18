package com.fooddelivery.restaurantservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.restaurantservice.dto.MenuRequest;
import com.fooddelivery.restaurantservice.dto.MenuResponse;

import com.fooddelivery.restaurantservice.entity.Menu;
import com.fooddelivery.restaurantservice.entity.Restaurant;
import com.fooddelivery.restaurantservice.exception.ResourceNotFoundException;
import com.fooddelivery.restaurantservice.repository.MenuRepository;
import com.fooddelivery.restaurantservice.repository.RestaurantRepository;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepo;

	@Autowired
	private RestaurantRepository restaurantRepo;

	/*
	 * BUSINESS LOGIC :
	 * Validate restaurant existence
	 * Create menu item entity
	 * Associate menu item with restaurant
	 * Save menu item into database
	 * Return menu response
	 */
	public MenuResponse addMenu(MenuRequest request) {
		Restaurant restaurant = restaurantRepo.findById(request.getRestaurantId())

				.orElseThrow(() -> new RuntimeException("Restaurant Not Found"));

		Menu menu = new Menu();
		menu.setItemName(request.getItemName());
		menu.setPrice(request.getPrice());
		menu.setRestaurant(restaurant);
		Menu saved = menuRepo.save(menu);
		return mapToResponse(saved);
	}

	
	
	/*
	 * BUSINESS LOGIC :
	 * Fetch all menu items from database
	 * Convert entities to response DTOs
	 * Return menu list
	 */
	public List<MenuResponse> getMenu() {
		return menuRepo.findAll().stream().map(this::mapToResponse).toList();
	}

	
	
	/*
	 * BUSINESS LOGIC :
	 * Find menu item by ID
	 * Validate menu existence
	 * Convert entity to response DTO
	 * Return menu details
	 */
	public MenuResponse getMenuById(Long id) {
		Menu menu = menuRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu not found!"));
		return mapToResponse(menu);
	}


	
	/*
	 * BUSINESS LOGIC :
	 * Find menu item by ID
	 * Validate menu existence
	 * Update menu item details
	 * Save updated menu item
	 * Return updated response
	 */
	public MenuResponse updateMenu(Long id, MenuRequest menu) {
		Menu res = menuRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu Not found!"));

		res.setItemName(menu.getItemName());
		res.setPrice(menu.getPrice());

		Menu updated = menuRepo.save(res);
		return mapToResponse(updated);
	}

	
	
	/*
	 * BUSINESS LOGIC :
	 * Find menu item by ID
	 * Validate menu existence
	 * Delete menu item from database
	 * Return success message
	 */
	public String deleteMenu(Long id) {
		Menu menu = menuRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu Not Found"));

		menuRepo.delete(menu);

		return "Menu Deleted";
	}

	
	/*
	 * BUSINESS LOGIC :
	 * Convert Menu entity to MenuResponse DTO
	 * Include restaurant details
	 * Return formatted response object
	 */
	private MenuResponse mapToResponse(Menu menu) {

		MenuResponse response = new MenuResponse();

		response.setId(menu.getId());
		response.setItemName(menu.getItemName());
		response.setPrice(menu.getPrice());
		response.setRestaurantId(menu.getRestaurant().getId());
		response.setRestaurantName(menu.getRestaurant().getName());
		return response;
	}

}
