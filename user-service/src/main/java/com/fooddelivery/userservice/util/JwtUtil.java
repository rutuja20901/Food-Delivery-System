package com.fooddelivery.userservice.util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	  private static final String SECRET =
	            "mysecretkeymysecretkeymysecretkey123456";

	    private Key key() {
	        return Keys.hmacShaKeyFor(SECRET.getBytes());
	    }

	 public String generateToken(String email,String role) {

	        return Jwts.builder()
	                .setSubject(email)
	                .claim("role", role)
	                .setIssuedAt(new Date())
	                .setExpiration(
	                    new Date(
	                        System.currentTimeMillis()
	                        + 1000*60*60
	                    )
	                )
	                .signWith(key())
	                .compact();
	    }
	 
	 public String extractUsername(String token){

		 return Jwts.parserBuilder()
	                .setSigningKey(key())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }
	 
	 public String extractRole(
		        String token){

		    Claims claims =
		            Jwts.parserBuilder()
		                    .setSigningKey(key())
		                    .build()
		                    .parseClaimsJws(token)
		                    .getBody();

		    return claims.get(
		            "role",
		            String.class
		    );
		}

	 public boolean validateToken(String token){

		 try{

	            Jwts.parserBuilder()
	                    .setSigningKey(key())
	                    .build()
	                    .parseClaimsJws(token);
	            System.out.println("True");
	            return true;

	        }catch(Exception e){
	        	 System.out.println("false" + e.getMessage());
	            return false;
	        }
	    }

	
}
