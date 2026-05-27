package com.fooddelivery.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fooddelivery.userservice.dto.LoginRequest;
import com.fooddelivery.userservice.dto.LoginResponse;
import com.fooddelivery.userservice.dto.RegisterRequest;
import com.fooddelivery.userservice.entity.User;
import com.fooddelivery.userservice.repository.UserRepository;
import com.fooddelivery.userservice.util.JwtUtil;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public String registerUser(RegisterRequest request) {
		if(userRepo.findByEmail(request.getEmail()).isPresent()){
			return "User Already Exists!";
		}
		
		User user = new User();

		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(
		        passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		user.setAddress(request.getAddress());
		user.setPhone(request.getPhone());

        userRepo.save(user);

        return "User Registered Successfully";
	}
	
	
	public LoginResponse login(
	        LoginRequest request) {

	    User user =
	    		userRepo.findByEmail(
	                    request.getEmail())
	            .orElseThrow(() ->
	                    new RuntimeException(
	                            "User not found"));

	    if(!passwordEncoder.matches(
	            request.getPassword(),
	            user.getPassword())) {

	        throw new RuntimeException(
	                "Invalid Password");
	    }

	    String token =
	            jwtUtil.generateToken(
	                    user.getEmail(),user.getRole());

	    return new LoginResponse(token);

	}
	
}
