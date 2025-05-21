package com.styleshop.controller;

import com.styleshop.dto.CarritoDTO;
import com.styleshop.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @GetMapping
    public List<CarritoDTO> listar(@RequestParam Long usuarioId) {
        return carritoService.obtenerCarrito(usuarioId);
    }

    @PostMapping
    public CarritoDTO agregar(@RequestBody CarritoDTO dto) {
        return carritoService.agregarAlCarrito(dto);
    }

    @PutMapping("/{id}")
    public CarritoDTO actualizar(@PathVariable Long id, @RequestBody CarritoDTO dto) {
        return carritoService.actualizarCarrito(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        carritoService.eliminarDelCarrito(id);
    }
}
