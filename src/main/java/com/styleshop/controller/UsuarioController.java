package com.styleshop.controller;

import com.styleshop.model.Usuario;
import com.styleshop.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios") // Ruta base para operaciones relacionadas con usuarios
@RequiredArgsConstructor // Inyección automática de dependencias vía constructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // GET /api/usuarios/email/{email}
    // Permite buscar un usuario por su email
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> obtenerPorEmail(@PathVariable String email) {
        try {
            Usuario usuario = usuarioService.buscarPorEmail(email); // Busca el usuario
            return ResponseEntity.ok(usuario); // Devuelve el usuario si se encuentra
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Devuelve 404 si no existe
        }
    }
}
