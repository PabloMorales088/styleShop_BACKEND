package com.styleshop.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.styleshop.config.JwtUtils;
import com.styleshop.config.SecurityConfig;
import com.styleshop.dto.LoginDto;
import com.styleshop.model.Usuario;
import com.styleshop.service.UsuarioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final UsuarioService userService;


    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UsuarioService userService, SecurityConfig securityConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto){
        System.out.println(loginDto.getEmail());
        System.out.println(loginDto.getPassword());
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);

        if (authentication.isAuthenticated()) {
            // Obtiene el usuario autenticado
            Optional<Usuario> optionalUser = userService.getByEmail(loginDto.getEmail());
            if (!optionalUser.isPresent() || optionalUser.get().isLocked() || optionalUser.get().isDisabled()) {
                return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
            }

            Usuario user = optionalUser.get();

            String jwt = this.jwtUtils.create(loginDto.getEmail());
            // Obtén el rol del usuario autenticado
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            String userRole = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario userEntity) {
        Usuario registeredUser = userService.save(userEntity);

        if (registeredUser == null) {
            return ResponseEntity
                    .badRequest()
                    .body("El nombre de usuario o email ya existe");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente");
    }

}
