package com.styleshop.controller;

import com.styleshop.dto.ProductoDTO;
import com.styleshop.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    // Crear producto
    @PostMapping
    public ProductoDTO crear(@RequestBody ProductoDTO dto) {
        return productoService.crearProducto(dto);
    }

    // Listar todos los productos
    @GetMapping
    public List<ProductoDTO> listar() {
        return productoService.listarProductos();
    }

    // Filtrar productos por categoriaId (por parámetro)
    @GetMapping(params = "categoriaId")
    public List<ProductoDTO> obtenerPorCategoria(@RequestParam Long categoriaId) {
        return productoService.listarPorCategoria(categoriaId);
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ProductoDTO obtener(@PathVariable Long id) {
        ProductoDTO producto = productoService.obtenerProducto(id);
        if (producto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        return producto;
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ProductoDTO actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        ProductoDTO actualizado = productoService.actualizarProducto(id, dto);
        if (actualizado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        return actualizado;
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }
}
