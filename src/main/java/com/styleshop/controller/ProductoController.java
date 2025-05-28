package com.styleshop.controller;

import com.styleshop.dto.ProductoDTO;
import com.styleshop.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/productos") // Ruta base para gestión de productos
@RequiredArgsConstructor // Genera constructor con dependencias inyectadas automáticamente
public class ProductoController {

    private final ProductoService productoService;

    // POST /api/productos
    // Crea un nuevo producto
    @PostMapping
    public ProductoDTO crear(@RequestBody ProductoDTO dto) {
        return productoService.crearProducto(dto);
    }

    // GET /api/productos
    // Lista todos los productos disponibles
    @GetMapping
    public List<ProductoDTO> listar() {
        return productoService.listarProductos();
    }

    // GET /api/productos?categoriaId=...
    // Lista productos que pertenecen a una categoría específica
    @GetMapping(params = "categoriaId")
    public List<ProductoDTO> obtenerPorCategoria(@RequestParam Long categoriaId) {
        return productoService.listarPorCategoria(categoriaId);
    }

    // GET /api/productos/{id}
    // Obtiene un producto por su ID. Lanza 404 si no existe.
    @GetMapping("/{id}")
    public ProductoDTO obtener(@PathVariable Long id) {
        ProductoDTO producto = productoService.obtenerProducto(id);
        if (producto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        return producto;
    }

    // PUT /api/productos/{id}
    // Actualiza un producto por su ID. Lanza 404 si no se encuentra.
    @PutMapping("/{id}")
    public ProductoDTO actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        ProductoDTO actualizado = productoService.actualizarProducto(id, dto);
        if (actualizado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        return actualizado;
    }

    // DELETE /api/productos/{id}
    // Elimina un producto por su ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }
}
