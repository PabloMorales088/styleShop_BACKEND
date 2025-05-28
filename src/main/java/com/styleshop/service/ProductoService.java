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
@RequiredArgsConstructor // Genera constructor con los campos finales (inyección automática)
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    // Crear un nuevo producto a partir de un DTO
    public ProductoDTO crearProducto(ProductoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId()).orElse(null); // Busca la categoría
        Producto producto = ProductoMapper.toEntity(dto, categoria); // Convierte el DTO en entidad
        return ProductoMapper.toDTO(productoRepository.save(producto)); // Guarda y devuelve el DTO
    }

    // Obtener todos los productos existentes
    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll().stream()
                .map(ProductoMapper::toDTO) // Mapea cada entidad a su DTO
                .collect(Collectors.toList());
    }

    // Obtener productos filtrados por ID de categoría
    public List<ProductoDTO> listarPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId).stream()
                .map(ProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar un producto por su ID
    public ProductoDTO obtenerProducto(Long id) {
        return productoRepository.findById(id)
                .map(ProductoMapper::toDTO) // Convierte a DTO si se encuentra
                .orElse(null); // Retorna null si no existe
    }

    // Actualizar los datos de un producto existente
    public ProductoDTO actualizarProducto(Long id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id).orElse(null); // Busca el producto actual
        if (producto == null) return null; // Si no existe, retorna null

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId()).orElse(null); // Nueva categoría
        ProductoMapper.updateEntityFromDTO(dto, producto, categoria); // Aplica cambios al objeto existente

        return ProductoMapper.toDTO(productoRepository.save(producto)); // Guarda y retorna el DTO actualizado
    }

    // Eliminar un producto por su ID
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id); // Borra de la base de datos
    }
}
