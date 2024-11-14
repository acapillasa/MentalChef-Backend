package com.mentalchef.security.jwt;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;

@Configuration
public class SecretKeyConf {

    @Value("${jwt.secret}")
    private String jwtsecret;

    @Bean
    public SecretKey key() {
        return Keys.hmacShaKeyFor(jwtsecret.getBytes());
    }
}
