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


@Tag(name="Menu APIs")
@RestController
@RequestMapping("/menu")
public class MenuController {

	
	@Autowired
	private MenuService menuService;
	
	@Operation(summary="Add Restaurant in Menu")
	@PostMapping
	public MenuResponse addRestaurant(@Valid @RequestBody MenuRequest res) {
		return menuService.addMenu(res);
	}
	
	@Operation(summary="Get All Menu")
	@GetMapping
	public List<MenuResponse> getMenu(){
		return menuService.getMenu();
	}
	
	@Operation(summary="Get Menu by Id")
	@GetMapping("/{id}")
	public MenuResponse getMenuById(@PathVariable Long id) {
		return menuService.getMenuById(id);
	}
	
	@Operation(summary="Update Menu by Id")
	@PutMapping("/{id}")
	public MenuResponse updateMenu(@Valid @RequestBody MenuRequest res, @PathVariable Long id) {
		return menuService.updateMenu(id, res);
	}
	
	@Operation(summary="Delete Menu by Id")
	@DeleteMapping("/{id}")
	public String deleteMenu(@PathVariable Long id) {
		return menuService.deleteMenu(id);
	}
}
