package com.styleshop.filter;

import java.io.IOException;

import com.styleshop.config.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Validar que el header Authorization esté presente, no esté vacío, y comience con "Bearer"
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response); // Si no cumple, continúa la cadena sin autenticar
            return;
        }

        // 2. Extraer el token JWT del header (eliminar el prefijo "Bearer")
        String jwt = authHeader.split(" ")[1].trim();

        // Validar el token usando JwtUtils
        if (!this.jwtUtils.isValid(jwt)) {
            filterChain.doFilter(request, response); // Si el token no es válido, continúa sin autenticar
            return;
        }

        // 3. Extraer el username del JWT y cargar los detalles del usuario desde el UserDetailsService
        String username = this.jwtUtils.getUSername(jwt);
        User user = (User) this.userDetailsService.loadUserByUsername(username);

        // 4. Crear un token de autenticación y establecerlo en el contexto de seguridad
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getUsername(), // Principal
                user.getPassword(), // Credentials (generalmente no se usan después de autenticación)
                user.getAuthorities() // Roles o permisos
        );

        SecurityContextHolder.getContext().setAuthentication(token); // Establece la autenticación

        // 5. Continuar con el siguiente filtro en la cadena
        filterChain.doFilter(request, response);
    }
}
