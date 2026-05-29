package com.fooddelivery.paymentservice.config;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

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