package com.styleshop.mapper;

import com.styleshop.dto.CategoriaDTO;
import com.styleshop.model.Categoria;

public class CategoriaMapper {

    public static Categoria toEntity(CategoriaDTO dto) {
        return Categoria.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .build();
    }

    public static CategoriaDTO toDTO(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre()
        );
    }
}
