package com.styleshop.service;

import com.styleshop.model.Usuario;
import com.styleshop.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Genera constructor para inyectar dependencias
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Guarda un nuevo usuario en la base de datos
    public Usuario save(Usuario userEntity) {
        if (userEntity.getPassword() == null || userEntity.getPassword().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        // Encripta la contraseña antes de guardar
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        // Por defecto, el usuario no está bloqueado ni deshabilitado
        userEntity.setLocked(false);
        userEntity.setDisabled(false);

        return usuarioRepository.save(userEntity);
    }

    // Valida credenciales de login (email y contraseña)
    public Usuario validarCredenciales(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verifica si la contraseña ingresada coincide con la almacenada (ya encriptada)
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }

    // Busca un usuario por su email
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
