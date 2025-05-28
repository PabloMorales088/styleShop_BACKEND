package com.styleshop.repository;

import com.styleshop.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Consulta derivada: obtiene todos los productos que pertenecen a una categoría específica (por ID de categoría)
    List<Producto> findByCategoriaId(Long categoriaId);
}
