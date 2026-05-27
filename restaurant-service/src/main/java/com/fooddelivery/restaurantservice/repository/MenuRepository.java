package com.fooddelivery.restaurantservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.restaurantservice.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{

}
