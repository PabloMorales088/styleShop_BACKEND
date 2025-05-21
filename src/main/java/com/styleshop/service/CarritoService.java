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
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<CarritoDTO> obtenerCarrito(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId).stream()
                .map(CarritoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CarritoDTO agregarAlCarrito(CarritoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElseThrow();
        Producto producto = productoRepository.findById(dto.getProductoId()).orElseThrow();

        List<Carrito> existentes = carritoRepository.findByUsuarioId(usuario.getId());

        for (Carrito c : existentes) {
            if (c.getProducto().getId().equals(producto.getId()) && c.getTalla().equals(dto.getTalla())) {
                c.setCantidad(c.getCantidad() + dto.getCantidad());
                return CarritoMapper.toDTO(carritoRepository.save(c));
            }
        }

        Carrito carrito = CarritoMapper.toEntity(dto, usuario, producto);
        return CarritoMapper.toDTO(carritoRepository.save(carrito));
    }

    public void eliminarDelCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    public CarritoDTO actualizarCarrito(Long id, CarritoDTO dto) {
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        if (carrito == null) return null;
        carrito.setCantidad(dto.getCantidad());
        carrito.setTalla(dto.getTalla());
        return CarritoMapper.toDTO(carritoRepository.save(carrito));
    }
}
