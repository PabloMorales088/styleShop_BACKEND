package com.styleshop.repository;

import com.styleshop.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Verifica si ya existe un usuario con el email especificado (útil para validación de registro)
    boolean existsByEmail(String email);

    // Busca un usuario por su email, devuelve Optional para manejar casos donde no se encuentra
    Optional<Usuario> findByEmail(String email);
}
