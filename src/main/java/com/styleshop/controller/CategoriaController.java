package com.styleshop.controller;

import com.styleshop.dto.CategoriaDTO;
import com.styleshop.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public CategoriaDTO crear(@RequestBody CategoriaDTO dto) {
        return categoriaService.crearCategoria(dto);
    }

    @GetMapping
    public List<CategoriaDTO> listar() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public CategoriaDTO buscar(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
    }
}

