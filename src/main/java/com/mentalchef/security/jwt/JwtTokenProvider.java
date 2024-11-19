package com.mentalchef.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mentalchef.demo.modelos.Usuario;
import com.mentalchef.security.CustomUserDetailService;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final SecretKey key;
    private final CustomUserDetailService customUserDetailsService;

    @Value("${jwt.expiration}")
    private Long jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        Usuario usuarioPrincipal = (Usuario) authentication.getPrincipal();
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(authToken);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String obtenerUsuarioDelToken(String authToken) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(authToken)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
