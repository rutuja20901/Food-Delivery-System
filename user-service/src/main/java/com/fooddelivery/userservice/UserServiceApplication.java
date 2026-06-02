package com.fooddelivery.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}


/*
 * TASKS :
 * USER SERVICE
│
├── Authentication
│     ├── Register
│     ├── Login
│     ├── Logout
│     ├── Refresh Token
│     ├── Forgot Password
│     └── Reset Password
│
├── User Management
│     ├── Get Profile
│     ├── Update Profile
│     ├── Change Password
│     ├── Delete Account
│     └── Upload Profile Picture
│
├── Address Management
│     ├── Add Address
│     ├── Get All Addresses
│     ├── Update Address
│     ├── Delete Address
│     └── Set Default Address
│
├── Role Management
│     ├── USER
│     ├── ADMIN
│     └── RESTAURANT_OWNER
│
└── Internal APIs
      ├── Get User By Id
      ├── Validate User
      └── Get User Details For Order Service
 * 
 */
