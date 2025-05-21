package com.styleshop.service;

import com.styleshop.dto.CategoriaDTO;
import com.styleshop.mapper.CategoriaMapper;
import com.styleshop.model.Categoria;
import com.styleshop.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaDTO crearCategoria(CategoriaDTO dto) {
        Categoria categoria = CategoriaMapper.toEntity(dto);
        categoria = categoriaRepository.save(categoria);
        return CategoriaMapper.toDTO(categoria);
    }

    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(CategoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        return (categoria != null) ? CategoriaMapper.toDTO(categoria) : null;
    }

    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}

