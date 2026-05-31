package com.fooddelivery.apigateway.config;


import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET =
            "YOUR_SAME_SECRET_KEY";

    private Key getKey() {

        return Keys.hmacShaKeyFor(
                SECRET.getBytes());
    }

    public boolean validateToken(
            String token){

        try {

            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch(Exception e) {

            return false;
        }
    }

    public Claims getClaims(
            String token){

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}