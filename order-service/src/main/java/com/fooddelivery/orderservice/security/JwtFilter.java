package com.fooddelivery.orderservice.security;


import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.
UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.
SecurityContextHolder;
import org.springframework.security.web.authentication.
WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
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

    	 System.out.println("JWT Filter Running");
        String authHeader =
                request.getHeader("Authorization");

        System.out.println(authHeader);
        if(authHeader != null &&
                authHeader.startsWith("Bearer ")) {

            String token =
                    authHeader.substring(7);
            System.out.println("token " +token);
            if(jwtUtil.validateToken(token)) {

                String username =
                        jwtUtil.extractUsername(token);
                
                System.out.println("user" +username);
                String role = jwtUtil.extractRole(token);
                System.out.println("role" +role);
                
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(
                                		new SimpleGrantedAuthority("ROLE_" + role)));
                auth.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));
                
                System.out.println("kjhgf" + auth.getAuthorities());

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(auth);
            }
        }

        filterChain.doFilter(
                request,
                response);
    }
}
