package com.styleshop.controller;

import com.styleshop.dto.CarritoDTO;
import com.styleshop.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito") // Ruta base para operaciones sobre el carrito
@RequiredArgsConstructor // Inyección automática de dependencias mediante constructor
public class CarritoController {

    private final CarritoService carritoService;

    // GET /api/carrito?usuarioId=...
    // Lista todos los ítems del carrito de un usuario específico
    @GetMapping
    public List<CarritoDTO> listar(@RequestParam Long usuarioId) {
        return carritoService.obtenerCarrito(usuarioId);
    }

    // POST /api/carrito
    // Agrega un nuevo ítem al carrito, o aumenta la cantidad si ya existe con misma talla
    @PostMapping
    public CarritoDTO agregar(@RequestBody CarritoDTO dto) {
        return carritoService.agregarAlCarrito(dto);
    }

    // PUT /api/carrito/{id}
    // Actualiza un ítem del carrito por ID (cantidad y/o talla)
    @PutMapping("/{id}")
    public CarritoDTO actualizar(@PathVariable Long id, @RequestBody CarritoDTO dto) {
        return carritoService.actualizarCarrito(id, dto);
    }

    // DELETE /api/carrito/{id}
    // Elimina un ítem del carrito por su ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        carritoService.eliminarDelCarrito(id);
    }
}
