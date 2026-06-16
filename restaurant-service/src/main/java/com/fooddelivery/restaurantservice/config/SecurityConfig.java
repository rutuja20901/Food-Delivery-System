package com.fooddelivery.restaurantservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
    public SecurityFilterChain
    securityFilterChain(
            HttpSecurity http)
            throws Exception {
		System.out.println("restaurant security is running");
        http
        .csrf(csrf -> csrf.disable())
        
        .authorizeHttpRequests(auth -> auth
        		.requestMatchers(
        			    "/swagger-ui/**",
        			    "/v3/api-docs/**",
        			    "/swagger-ui.html"
        			).permitAll()
        .requestMatchers("/restaurant/internal/**").permitAll()
        .requestMatchers(HttpMethod.POST,"/restaurant").hasRole("RESTAURANT_OWNER")
        .requestMatchers("/restaurant/*/approve").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/restaurant").hasAnyRole("CUSTOMER","ADMIN","RESTAURANT_OWNER")
        .requestMatchers(HttpMethod.GET,"/restaurant/**").hasAnyRole("CUSTOMER","ADMIN","RESTAURANT_OWNER")
        .requestMatchers(HttpMethod.PUT,"/restaurant/**").hasAnyRole("ADMIN","RESTAURANT_OWNER")
        .requestMatchers(HttpMethod.DELETE,"/restaurant").hasAnyRole("ADMIN")
        .requestMatchers("/menu/**").permitAll()
        .anyRequest()
        .authenticated()).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
	}
}
