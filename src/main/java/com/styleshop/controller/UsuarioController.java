package com.styleshop.controller;

import com.styleshop.dto.UsuarioDTO;
import com.styleshop.dto.UsuarioInfoDTO;
import com.styleshop.model.Usuario;
import com.styleshop.service.UsuarioService;
import com.styleshop.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
/*
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/registro")
    public String registrar(@RequestBody UsuarioDTO dto) {
        return usuarioService.registrar(dto);
    }

    @PostMapping("/iniciar-sesion")
    public String iniciarSesion(@RequestBody UsuarioDTO dto) {
        return usuarioService.iniciarSesion(dto);
    }

    @GetMapping("/email/{email}")
    public UsuarioInfoDTO obtenerPorEmail(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        return new UsuarioInfoDTO(usuario.get().getId(), usuario.get().getNombre(), usuario.get().getEmail());
    }


 */
}
