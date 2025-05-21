package com.styleshop.repository;

import com.styleshop.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> getByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
