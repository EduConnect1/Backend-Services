package com.example.demo.core.security;


import com.example.demo.auth.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secret;

    public String generateAccessToken(User user) {
        String role = user.getRoles().stream()
                .findFirst()
                .map(r -> r.getRole().name())
                .orElse("ROLE_USER");

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", role)
                .claim("id", user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public String generateRefreshToken(User user) {
        String role = user.getRoles().stream()
                .findFirst()
                .map(r -> r.getRole().name())
                .orElse("ROLE_USER");

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", role)
                .claim("id", user.getId())
                .claim("type", "refresh")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 7 days
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.replace("Bearer ", "");
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return !isTokenExpired(claims) && !isTokenType(claims, "refresh");
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Claims claims = getClaims(token);
            return !isTokenExpired(claims) && isTokenType(claims, "refresh");
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    private boolean isTokenType(Claims claims, String type) {
        return type.equals(claims.get("type", String.class));
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getEmailFromToken(String token){
        return getClaims(token).getSubject();
    }

    public String getRoleFromToken(String token){
        return getClaims(token).get("role", String.class);
    }

    public String generateOtp(){
        return String.valueOf((int)(Math.random() * 900000) + 100000);
    }

    public String generateResetToken(String email) {
        return UUID.randomUUID().toString();
    }
}
