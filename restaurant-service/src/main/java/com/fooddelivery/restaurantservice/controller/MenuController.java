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

import com.fooddelivery.restaurantservice.dto.MenuRequest;
import com.fooddelivery.restaurantservice.dto.MenuResponse;
import com.fooddelivery.restaurantservice.service.MenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Menu APIs")
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	
	/*
	 * API : ADD MENU ITEM
	 * Create a new menu item for a restaurant
	 * Accepts menu item details and restaurant ID
	 */
	@Operation(summary = "Add Restaurant in Menu")
	@PostMapping
	public MenuResponse addRestaurant(@Valid @RequestBody MenuRequest res) {
		return menuService.addMenu(res);
	}

	
	/*
	 * API : GET ALL MENU ITEMS
	 * Retrieve all menu items
	 * Returns menu details with restaurant information
	 */
	@Operation(summary = "Get All Menu")
	@GetMapping
	public List<MenuResponse> getMenu() {
		return menuService.getMenu();
	}

	
	/*
	 * API : GET MENU BY ID
	 * Retrieve menu item details using menu ID
	 */
	@Operation(summary = "Get Menu by Id")
	@GetMapping("/{id}")
	public MenuResponse getMenuById(@PathVariable Long id) {
		return menuService.getMenuById(id);
	}

	
	/*
	 * API : UPDATE MENU ITEM
	 * Update menu item information by menu ID
	 */
	@Operation(summary = "Update Menu by Id")
	@PutMapping("/{id}")
	public MenuResponse updateMenu(@Valid @RequestBody MenuRequest res, @PathVariable Long id) {
		return menuService.updateMenu(id, res);
	}

	
	/*
	 * API : DELETE MENU ITEM
	 * Remove menu item from database using menu ID
	 */
	@Operation(summary = "Delete Menu by Id")
	@DeleteMapping("/{id}")
	public String deleteMenu(@PathVariable Long id) {
		return menuService.deleteMenu(id);
	}
}
