package com.fooddelivery.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.userservice.entity.User;

@Repository
public interface AdminRepository extends JpaRepository<User,Long>{

}
