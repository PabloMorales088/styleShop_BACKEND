package com.styleshop.mapper;

import com.styleshop.dto.UsuarioDTO;
import com.styleshop.model.Usuario;

public class UsuarioMapper {

    // Convierte un DTO de Usuario a una entidad Usuario
    public static Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre()); // Asigna el nombre desde el DTO
        usuario.setEmail(dto.getEmail()); // Asigna el email desde el DTO
        usuario.setPassword(dto.getPassword()); // Asigna la contraseña (normalmente cifrada antes de guardar)
        return usuario;
    }
}
