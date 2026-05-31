package com.fooddelivery.restaurantservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
    public SecurityFilterChain
    securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
        .csrf(csrf -> csrf.disable())

        .authorizeHttpRequests(auth -> auth

        .requestMatchers("/restaurant").hasRole("RESTAURANT_OWNER")
        .requestMatchers("/restaurant/*/approve").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/restaurants").hasAnyRole("CUSTOMER","ADMIN","RESTAURANT_OWNER")
        .requestMatchers(HttpMethod.GET,"/restaurants/**").hasAnyRole("CUSTOMER","ADMIN","RESTAURANT_OWNER")
        .requestMatchers(HttpMethod.PUT,"/restaurants/**").hasAnyRole("ADMIN","RESTAURANT_OWNER")
        .requestMatchers(HttpMethod.DELETE,"/restaurants").hasAnyRole("ADMIN")
        .anyRequest()
        .authenticated());

        return http.build();
	}
}
