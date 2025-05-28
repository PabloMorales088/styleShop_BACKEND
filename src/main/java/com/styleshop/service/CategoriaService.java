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
@RequiredArgsConstructor // Genera constructor con todos los campos finales (inyección por constructor)
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Crea una nueva categoría a partir de un DTO y la guarda en la base de datos
    public CategoriaDTO crearCategoria(CategoriaDTO dto) {
        Categoria categoria = CategoriaMapper.toEntity(dto); // Convierte el DTO a entidad
        categoria = categoriaRepository.save(categoria); // Guarda en la base de datos
        return CategoriaMapper.toDTO(categoria); // Devuelve el DTO resultante
    }

    // Retorna la lista completa de categorías como DTOs
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(CategoriaMapper::toDTO) // Convierte cada entidad a DTO
                .collect(Collectors.toList());
    }

    // Busca una categoría por ID y la devuelve como DTO si existe, o null si no
    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        return (categoria != null) ? CategoriaMapper.toDTO(categoria) : null;
    }

    // Elimina una categoría por su ID
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
