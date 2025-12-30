package com.example.PropertyRent.Config;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtService {

    private final String SECRET_KEY = "MyJwtSecretKeyMyJwtSecretKeyMyJwtSecretKey1234567890";
    private final long EXPIRATION = 3600000; // 1 day

    @SuppressWarnings("deprecation")
	public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @SuppressWarnings("deprecation")
	public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

