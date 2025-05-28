package com.styleshop.controller;

import com.styleshop.dto.CategoriaDTO;
import com.styleshop.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias") // Ruta base para la gestión de categorías
@RequiredArgsConstructor // Inyección automática del servicio mediante constructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    // POST /api/categorias
    // Crea una nueva categoría a partir del DTO recibido
    @PostMapping
    public CategoriaDTO crear(@RequestBody CategoriaDTO dto) {
        return categoriaService.crearCategoria(dto);
    }

    // GET /api/categorias
    // Lista todas las categorías registradas
    @GetMapping
    public List<CategoriaDTO> listar() {
        return categoriaService.listarCategorias();
    }

    // GET /api/categorias/{id}
    // Busca una categoría específica por su ID
    @GetMapping("/{id}")
    public CategoriaDTO buscar(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    // DELETE /api/categorias/{id}
    // Elimina una categoría por su ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
    }
}
