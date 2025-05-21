package com.styleshop.mapper;

import com.styleshop.dto.CarritoDTO;
import com.styleshop.model.Carrito;
import com.styleshop.model.Producto;
import com.styleshop.model.Usuario;

public class CarritoMapper {

    public static Carrito toEntity(CarritoDTO dto, Usuario usuario, Producto producto) {
        return Carrito.builder()
                .id(dto.getId())
                .usuario(usuario)
                .producto(producto)
                .cantidad(dto.getCantidad())
                .talla(dto.getTalla())
                .build();
    }

    public static CarritoDTO toDTO(Carrito carrito) {
        return new CarritoDTO(
                carrito.getId(),
                carrito.getProducto().getId(),
                carrito.getCantidad(),
                carrito.getTalla(),
                carrito.getUsuario().getId()
        );
    }
}
