package com.styleshop.mapper;

import com.styleshop.dto.ProductoDTO;
import com.styleshop.model.Categoria;
import com.styleshop.model.Producto;

public class ProductoMapper {

    // Convierte un DTO de Producto a una entidad Producto, asociando la categoría correspondiente
    public static Producto toEntity(ProductoDTO dto, Categoria categoria) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre()); // Nombre del producto
        p.setDescripcion(dto.getDescripcion()); // Descripción del producto
        p.setPrecio(dto.getPrecio()); // Precio
        p.setTalla(dto.getTalla()); // Talla
        p.setStock(dto.getStock()); // Stock disponible
        p.setImagen(dto.getImagen()); // Nombre/ruta de la imagen
        p.setCategoria(categoria); // Categoría asociada
        return p;
    }

    // Convierte una entidad Producto a su correspondiente DTO
    public static ProductoDTO toDTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId()); // ID del producto
        dto.setNombre(p.getNombre()); // Nombre
        dto.setDescripcion(p.getDescripcion()); // Descripción
        dto.setPrecio(p.getPrecio()); // Precio
        dto.setTalla(p.getTalla()); // Talla
        dto.setStock(p.getStock()); // Stock
        dto.setImagen(p.getImagen()); // Imagen
        dto.setCategoriaId(p.getCategoria() != null ? p.getCategoria().getId() : null);
        // Se asigna el ID de la categoría si existe, para evitar NullPointerException
        return dto;
    }

    // Actualiza una entidad Producto existente con los datos de un DTO y una nueva categoría
    public static void updateEntityFromDTO(ProductoDTO dto, Producto producto, Categoria categoria) {
        producto.setNombre(dto.getNombre()); // Actualiza nombre
        producto.setDescripcion(dto.getDescripcion()); // Actualiza descripción
        producto.setPrecio(dto.getPrecio()); // Actualiza precio
        producto.setTalla(dto.getTalla()); // Actualiza talla
        producto.setStock(dto.getStock()); // Actualiza stock
        producto.setImagen(dto.getImagen()); // Actualiza imagen
        producto.setCategoria(categoria); // Actualiza categoría asociada
    }
}
