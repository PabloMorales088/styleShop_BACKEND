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
@RequestMapping("/auth") // Ruta base para autenticación
@RequiredArgsConstructor // Genera constructor con dependencias inyectadas automáticamente
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtils jwtUtils;

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/registro")
    public ResponseEntity<Map<String, String>> registrar(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.save(usuario); // Guarda el usuario
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Usuario registrado correctamente con ID: " + nuevo.getId());
        return ResponseEntity.ok(response); // Devuelve un mensaje de éxito
    }

    // Endpoint para iniciar sesión y obtener un token JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        // Valida email y contraseña
        Usuario user = usuarioService.validarCredenciales(usuario.getEmail(), usuario.getPassword());

        // Genera el token JWT usando el email como "username"
        String token = jwtUtils.create(user.getEmail());

        // Devuelve el token en el cuerpo de la respuesta
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
