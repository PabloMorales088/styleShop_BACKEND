package com.styleshop.controller;

import com.styleshop.config.JwtUtils;
import com.styleshop.model.Usuario;
import com.styleshop.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtils jwtUtils;

    @PostMapping("/registro")
    public ResponseEntity<Map<String, String>> registrar(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.save(usuario);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Usuario registrado correctamente con ID: " + nuevo.getId());
        return ResponseEntity.ok(response);
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Usuario user = usuarioService.validarCredenciales(usuario.getEmail(), usuario.getPassword());
        String token = jwtUtils.create(user.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
