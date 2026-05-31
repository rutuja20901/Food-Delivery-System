package com.fooddelivery.paymentservice.config;


import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String SECRET =
            "mysecretkeymysecretkeymysecretkey123456";

    private Key getKey() {
        return Keys.hmacShaKeyFor(
                SECRET.getBytes());
    }

    public String extractUsername(String token){

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){

        try{
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);

            return true;

        }catch(Exception e){
            return false;
        }
    }
    
    public String extractRole(
	        String token){

	    Claims claims =
	            Jwts.parserBuilder()
	                    .setSigningKey(getKey())
	                    .build()
	                    .parseClaimsJws(token)
	                    .getBody();

	    return claims.get(
	            "role",
	            String.class
	    );
	}
}