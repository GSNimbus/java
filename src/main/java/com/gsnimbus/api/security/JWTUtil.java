package com.gsnimbus.api.security;

import com.gsnimbus.api.exception.TokenValidationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey privateKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String buildToken(String username) {
        Date actualDate = new Date();

        JwtBuilder builder = Jwts.builder()
                .subject(username)
                .issuedAt(actualDate)
                .expiration(new Date(actualDate.getTime() + (3600000)))
                .signWith(privateKey);
        return builder.compact();
    }

    public String extractUsername(String token) {
        JwtParser parser = Jwts.parser().verifyWith(privateKey).build();
        return parser.parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(privateKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            throw new TokenValidationException("Token inválido", e);
        } catch (IllegalArgumentException e){
            throw new TokenValidationException("Token mal formado", e);
        }

    }

}
