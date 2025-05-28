package com.styleshop.service;

import com.styleshop.dto.CarritoDTO;
import com.styleshop.mapper.CarritoMapper;
import com.styleshop.model.Carrito;
import com.styleshop.model.Producto;
import com.styleshop.model.Usuario;
import com.styleshop.repository.CarritoRepository;
import com.styleshop.repository.ProductoRepository;
import com.styleshop.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Genera constructor con los campos finales (inyección de dependencias)
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    // Devuelve el carrito completo de un usuario en formato DTO
    public List<CarritoDTO> obtenerCarrito(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId).stream()
                .map(CarritoMapper::toDTO) // Convierte cada entidad a DTO
                .collect(Collectors.toList());
    }

    // Agrega un producto al carrito. Si ya existe con misma talla, incrementa la cantidad
    public CarritoDTO agregarAlCarrito(CarritoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElseThrow(); // Obtiene usuario
        Producto producto = productoRepository.findById(dto.getProductoId()).orElseThrow(); // Obtiene producto

        List<Carrito> existentes = carritoRepository.findByUsuarioId(usuario.getId()); // Carrito actual del usuario

        // Verifica si el producto con la misma talla ya está en el carrito
        for (Carrito c : existentes) {
            if (c.getProducto().getId().equals(producto.getId()) && c.getTalla().equals(dto.getTalla())) {
                c.setCantidad(c.getCantidad() + dto.getCantidad()); // Suma cantidad
                return CarritoMapper.toDTO(carritoRepository.save(c)); // Actualiza y retorna
            }
        }

        // Si no existe, se crea una nueva entrada en el carrito
        Carrito carrito = CarritoMapper.toEntity(dto, usuario, producto);
        return CarritoMapper.toDTO(carritoRepository.save(carrito));
    }

    // Elimina un ítem del carrito por ID
    public void eliminarDelCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    // Actualiza un ítem del carrito (cantidad y talla) por ID
    public CarritoDTO actualizarCarrito(Long id, CarritoDTO dto) {
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        if (carrito == null) return null;

        carrito.setCantidad(dto.getCantidad()); // Actualiza cantidad
        carrito.setTalla(dto.getTalla()); // Actualiza talla
        return CarritoMapper.toDTO(carritoRepository.save(carrito)); // Guarda y retorna DTO actualizado
    }
}
