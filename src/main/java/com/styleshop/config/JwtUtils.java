package com.styleshop.config;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JwtUtils {

    @Value("${jwt.secret:StyleShop}")
    private String secret;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("StyleShop")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(getAlgorithm());
    }

    public boolean isValid(String jwt) {
        try {
            JWT.require(getAlgorithm()).build().verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUSername(String jwt) {
        return JWT.require(getAlgorithm()).build().verify(jwt).getSubject();
    }
}
