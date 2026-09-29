package com.workpilot_backend.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long expiration;
    private Key key;
    public JwtService(){}

    public String generateToken(UserDetails userDetails){

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(key)
                .compact();

    }
    @PostConstruct
    public void init(){

        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    public String extractEmail(String token){

       return getClaims(token).getSubject();

    }
    public boolean validateToken(String token, UserDetails user){
        try {
            Claims claims = getClaims(token);

            Date expiration = claims.getExpiration();
            String email = claims.getSubject();

            return (email.equals(user.getUsername()) && expiration.after(new Date()));
        }catch (Exception e){
            return false;
        }
    }
    public Claims getClaims(String token){
        return  Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
    }
}
