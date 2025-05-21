package com.styleshop.service;

import com.styleshop.dto.ProductoDTO;
import com.styleshop.mapper.ProductoMapper;
import com.styleshop.model.Categoria;
import com.styleshop.model.Producto;
import com.styleshop.repository.CategoriaRepository;
import com.styleshop.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    // Crear producto
    public ProductoDTO crearProducto(ProductoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId()).orElse(null);
        Producto producto = ProductoMapper.toEntity(dto, categoria);
        return ProductoMapper.toDTO(productoRepository.save(producto));
    }

    // Listar todos los productos
    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll().stream()
                .map(ProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Listar productos por categoría
    public List<ProductoDTO> listarPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId).stream()
                .map(ProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener producto por ID
    public ProductoDTO obtenerProducto(Long id) {
        return productoRepository.findById(id)
                .map(ProductoMapper::toDTO)
                .orElse(null);
    }

    // Actualizar producto
    public ProductoDTO actualizarProducto(Long id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto == null) return null;

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId()).orElse(null);
        ProductoMapper.updateEntityFromDTO(dto, producto, categoria);

        return ProductoMapper.toDTO(productoRepository.save(producto));
    }

    // Eliminar producto
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
