package com.styleshop.mapper;

import com.styleshop.dto.CarritoDTO;
import com.styleshop.model.Carrito;
import com.styleshop.model.Producto;
import com.styleshop.model.Usuario;

public class CarritoMapper {

    // Convierte un DTO de carrito a una entidad Carrito, incluyendo las referencias a Usuario y Producto
    public static Carrito toEntity(CarritoDTO dto, Usuario usuario, Producto producto) {
        return Carrito.builder()
                .id(dto.getId()) // ID del carrito (útil para actualizaciones)
                .usuario(usuario) // Usuario asociado al carrito
                .producto(producto) // Producto asociado al carrito
                .cantidad(dto.getCantidad()) // Cantidad solicitada
                .talla(dto.getTalla()) // Talla del producto
                .build();
    }

    // Convierte una entidad Carrito a su DTO correspondiente para transporte de datos (por ejemplo, en respuestas API)
    public static CarritoDTO toDTO(Carrito carrito) {
        return new CarritoDTO(
                carrito.getId(), // ID del carrito
                carrito.getProducto().getId(), // Solo se envía el ID del producto
                carrito.getCantidad(), // Cantidad solicitada
                carrito.getTalla(), // Talla seleccionada
                carrito.getUsuario().getId() // Solo se envía el ID del usuario
        );
    }
}
