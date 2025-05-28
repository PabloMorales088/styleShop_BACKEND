package com.styleshop.mapper;

import com.styleshop.dto.CategoriaDTO;
import com.styleshop.model.Categoria;

public class CategoriaMapper {

    // Convierte un DTO de categoría a una entidad Categoria
    public static Categoria toEntity(CategoriaDTO dto) {
        return Categoria.builder()
                .id(dto.getId()) // ID (puede ser útil para actualizaciones)
                .nombre(dto.getNombre()) // Nombre de la categoría
                .build(); // La lista de productos no se incluye aquí (no es necesaria para esta conversión básica)
    }

    // Convierte una entidad Categoria a su DTO correspondiente
    public static CategoriaDTO toDTO(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getId(), // ID de la categoría
                categoria.getNombre() // Nombre de la categoría
        );
    }
}
