package com.styleshop.repository;

import com.styleshop.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    // Consulta personalizada: obtiene todos los carritos asociados a un usuario específico por su ID
    List<Carrito> findByUsuarioId(Long usuarioId);
}
