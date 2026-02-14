package dev.joaquincoronado.betterme.auth.service;

import dev.joaquincoronado.betterme.user.model.BettermeUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JWTService {

    private String JWT_SECRET_KEY = "your_secret_key_here";

    public String generateToken(BettermeUser user){
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(Date.from(now.plus(7, ChronoUnit.DAYS)))
                .claim("role", user.getRole())
                .claim("id", user.getId())
                .signWith(Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
            .compact();
    }

    public BettermeUser getUserFromToken(String token){

        Claims claims = Jwts.parserBuilder()
            .setSigningKey(JWT_SECRET_KEY.getBytes())
            .build()
            .parseClaimsJws(token)
            .getBody();

        String email = claims.getSubject();
        Long id = claims.get("id", Long.class);
        String role = claims.get("role", String.class);

        return BettermeUser.builder()
            .id(id)
            .email(email)
            .role(BettermeUser.Role.valueOf(role))
            .build();
    }

}
