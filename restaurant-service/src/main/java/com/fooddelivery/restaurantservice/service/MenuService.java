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

	// Add menu
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

	// Get all details
	public List<MenuResponse> getMenu() {
		return menuRepo.findAll().stream().map(this::mapToResponse).toList();
	}

	// Get menu by id
	public MenuResponse getMenuById(Long id) {
		Menu menu = menuRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu not found!"));
		return mapToResponse(menu);
	}

	// Update menu by id
	public MenuResponse updateMenu(Long id, MenuRequest menu) {
		Menu res = menuRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu Not found!"));

		res.setItemName(menu.getItemName());
		res.setPrice(menu.getPrice());

		Menu updated = menuRepo.save(res);
		return mapToResponse(updated);
	}

	// Delete menu

	public String deleteMenu(Long id) {
		Menu menu = menuRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu Not Found"));

		menuRepo.delete(menu);

		return "Menu Deleted";
	}

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
