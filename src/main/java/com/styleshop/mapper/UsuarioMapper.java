package com.styleshop.mapper;

import com.styleshop.dto.UsuarioDTO;
import com.styleshop.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        return usuario;
    }
}
