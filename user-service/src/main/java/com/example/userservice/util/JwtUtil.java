package com.example.userservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        byte[] encodedKey = Base64.getEncoder().encode(secret.getBytes(
                StandardCharsets.UTF_8));
        this.secretKey = Keys.hmacShaKeyFor(encodedKey);
    }

    public String generateToken(String email, String role) {
        return Jwts.builder().subject(email).claim("role", role)
                .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 10))
                .signWith(secretKey).compact();
    }
}
