package com.styleshop.config;

import com.styleshop.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    // Filtro JWT personalizado que se ejecuta antes del filtro estándar de autenticación
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desactiva la protección CSRF, útil para APIs REST
                .cors().and() // Habilita CORS
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // No se usará sesión (stateless)
                .authorizeHttpRequests()

                // Rutas abiertas para autenticación y registro
                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()

                // Rutas GET públicas para consultar productos, categorías e imágenes
                .requestMatchers(HttpMethod.GET, "/api/categorias/**", "/api/productos/**", "/imagenes/**").permitAll()

                // Rutas protegidas: requieren autenticación con JWT
                .requestMatchers("/api/pedidos/**").authenticated()
                .requestMatchers("/api/carrito/**").authenticated()

                // Cualquier otra petición es permitida (útil para pruebas, pero podría limitarse en producción)
                .anyRequest().permitAll()
                .and()

                // Inserta el filtro JWT antes del filtro de autenticación por usuario/contraseña
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Construye la cadena de filtros de seguridad
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Codificador de contraseñas usando BCrypt
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager(); // Devuelve el AuthenticationManager configurado automáticamente por Spring
    }
}
