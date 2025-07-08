package com.cybersoft.capstone.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String data, String role) {
        Map<String, String> claims = new HashMap<>();
        claims.put("role", role);
        return Jwts
          .builder()
          .claims(claims)
          .subject(data)
          .issuedAt(new Date(System.currentTimeMillis()))
          .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
          .signWith(getSecretKey())
          .compact();
    }

    public String decodeToken(String token) {
        String data = null;

        try {
            data = Jwts
              .parser()
              .verifyWith(getSecretKey())
              .build()
              .parseSignedClaims(token)
              .getPayload()
              .getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println("Token Expired!");
        } catch (JwtException e) {
            System.out.println("Decode Token!");
        }

        return data;
    }

    public String extractRole(String token) {
        String role = null;
        try {
            Claims claims = Jwts
              .parser()
              .verifyWith(getSecretKey())
              .build()
              .parseSignedClaims(token)
              .getPayload();
            role = claims.get("role").toString();
        } catch (ExpiredJwtException e) {
            System.out.println("Token Expired!");
        } catch (JwtException e) {
            System.out.println("Decode Token!");
        }
        return role;
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

}
