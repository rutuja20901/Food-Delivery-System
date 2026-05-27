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
import com.fooddelivery.restaurantservice.entity.Menu;
import com.fooddelivery.restaurantservice.service.MenuService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/menu")
public class MenuController {

	
	@Autowired
	private MenuService menuService;
	
	@PostMapping
	public MenuResponse addRestaurant(@Valid @RequestBody MenuRequest res) {
		return menuService.addMenu(res);
	}
	
	@GetMapping
	public List<MenuResponse> getMenu(){
		return menuService.getMenu();
	}
	
	@GetMapping("/{id}")
	public MenuResponse getMenuById(@PathVariable Long id) {
		return menuService.getMenuById(id);
	}
	
	@PutMapping("/{id}")
	public MenuResponse updateMenu(@Valid @RequestBody MenuRequest res, @PathVariable Long id) {
		return menuService.updateMenu(id, res);
	}
	
	@DeleteMapping("/{id}")
	public String deleteMenu(@PathVariable Long id) {
		return menuService.deleteMenu(id);
	}
}
