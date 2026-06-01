package com.fooddelivery.restaurantservice.config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter
        extends OncePerRequestFilter {

	@Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

    	
    	System.out.println("Restaurant jwtFilter is running");
        String authHeader =
                request.getHeader(
                    "Authorization");

        if(authHeader != null
                && authHeader.startsWith(
                    "Bearer ")) {

            String token =
                    authHeader.substring(7);

            String username =
                    jwtUtil.extractUsername(
                            token);

            String role =
                    jwtUtil.extractRole(
                            token);

            List<SimpleGrantedAuthority>
                    authorities =
                    Collections.singletonList(

                        new SimpleGrantedAuthority(
                                "ROLE_" + role));

            UsernamePasswordAuthenticationToken
                    authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities);

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                            authentication);
        }

        filterChain.doFilter(
                request,
                response);
    }
}