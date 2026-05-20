package com.mybank.service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private String secretKey = "";

    public JWTService() {

        try {

            KeyGenerator keygen =
                    KeyGenerator.getInstance("HmacSHA256");

            keygen.init(256);

            SecretKey sk = keygen.generateKey();

            secretKey = Base64.getEncoder()
                    .encodeToString(sk.getEncoded());

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
    
    // generate token
    public String generateToken(String name) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(name)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(getKey())
                .compact();
    }


    private Key getKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token,
                                 UserDetails userDetails) {

        final String username = extractUserName(token);

        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token,
                              Function<Claims, T> resolver) {

        final Claims claims = extractAllClaims(token);

        return resolver.apply(claims);
    }

       private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}