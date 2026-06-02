package com.fooddelivery.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.userservice.entity.User;

@Repository
public interface AuthRepository extends JpaRepository<User,Long>{

	Optional<User> findByEmail(String email);

}
