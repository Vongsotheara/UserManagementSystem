package com.CRUDOperation.usermanangementsystem.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
	private static final String SECRET = "my-very-secret-key-that-is-long-enough-12345";
	private static final long EXPIRATION_MS = 86400000;// 24 hours

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
		
	}
	// Called after login — creates a token with the username baked in
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	// Pulls the username out of the token (used in JwtAuthFilter)
	public String extractUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	// Validates that the token hasn't been tampered with and hasn't expired
	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
   
}