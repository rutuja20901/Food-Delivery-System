package com.fooddelivery.userservice.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.fooddelivery.userservice.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtFilter extends GenericFilter{

	@Autowired
	private JwtUtil jwtUtil;
	

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)

            throws IOException,
            ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        
        String header =
                req.getHeader("Authorization");

        if(header != null &&
                header.startsWith("Bearer ")){
        	String token =
                    header.substring(7);
        	

            if(jwtUtil.validateToken(token)){

                String email =
                        jwtUtil.extractUsername(
                                token);
                String role = jwtUtil.extractRole(token);
                
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(
                                		new SimpleGrantedAuthority("ROLE_" + role)));

                
                
                auth.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(req));


                SecurityContextHolder
                        .getContext()
                        .setAuthentication(auth);
            }
        }

        chain.doFilter(request,response);
    }
}
