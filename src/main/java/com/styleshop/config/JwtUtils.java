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

    // Inyecta el valor del secreto JWT desde application.properties o usa "StyleShop" como valor por defecto
    @Value("${jwt.secret:StyleShop}")
    private String secret;

    // Método privado que devuelve el algoritmo HMAC256 usando el secreto configurado
    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    // Crea un token JWT para el nombre de usuario dado
    public String create(String username) {
        return JWT.create()
                .withSubject(username) // Establece el sujeto del token (el usuario)
                .withIssuer("StyleShop") // Define el emisor del token
                .withIssuedAt(new Date()) // Fecha de emisión (ahora)
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15))) // Fecha de expiración (15 días)
                .sign(getAlgorithm()); // Firma el token con el algoritmo configurado
    }

    // Verifica si el token JWT es válido (estructura y firma)
    public boolean isValid(String jwt) {
        try {
            JWT.require(getAlgorithm()).build().verify(jwt); // Verifica la firma y estructura del token
            return true;
        } catch (JWTVerificationException e) {
            return false; // Retorna falso si hay cualquier problema en la verificación
        }
    }

    // Extrae el nombre de usuario (sujeto) del token JWT
    public String getUSername(String jwt) {
        return JWT.require(getAlgorithm()).build().verify(jwt).getSubject();
    }
}
