package com.styleshop.service;

import com.styleshop.config.SecurityConfig;
import com.styleshop.dto.UsuarioDTO;
import com.styleshop.model.Usuario;
import com.styleshop.repository.UsuarioRepository;
import com.styleshop.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository userRepository;
    private final SecurityConfig securityConfig;


/*
    public String registrar(UsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            return "El correo ya está registrado.";
        }
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuarioRepository.save(usuario);
        return "Usuario registrado correctamente.";
    }

    public String iniciarSesion(UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
        if (usuario == null) {
            return "Usuario no encontrado.";
        }
        if (!usuario.getContraseña().equals(dto.getContraseña())) {
            return "Contraseña incorrecta.";
        }
        return "Inicio de sesión correcto.";
    }
*/
    public Optional<Usuario> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Usuario save(Usuario userEntity) {
        // Si ya existe por nombre o email, no se guarda
        if (userRepository.existsByNombre(userEntity.getNombre()) ||
                userRepository.existsByEmail(userEntity.getEmail())) {
            return null; // O puedes devolver un usuario vacío o con un campo indicador
        }

        // Datos comunes
        userEntity.setPassword(this.securityConfig.passwordEncoder().encode(userEntity.getPassword()));
        userEntity.setLocked(false);
        userEntity.setDisabled(false);
        userEntity.setRoleId(2L); // Asignar ROLE_USER por defecto

        return userRepository.save(userEntity);
    }

}

