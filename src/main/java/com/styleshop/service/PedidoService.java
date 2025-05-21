package com.styleshop.service;

import com.styleshop.model.*;
import com.styleshop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final UsuarioRepository usuarioRepository;
    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;

    @Transactional
    public void confirmarPedido(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        List<Carrito> itemsCarrito = carritoRepository.findByUsuarioId(usuarioId);
        if (itemsCarrito.isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        for (Carrito item : itemsCarrito) {
            Producto producto = item.getProducto();
            if (producto.getStock() < item.getCantidad()) {
                throw new IllegalStateException("Stock insuficiente para " + producto.getNombre());
            }
        }

        Pedido pedido = Pedido.builder()
                .usuario(usuario)
                .fecha(LocalDateTime.now())
                .estado(Pedido.Estado.Procesando)
                .total(0.0)
                .detalles(new ArrayList<>())
                .build();

        double total = 0;

        for (Carrito item : itemsCarrito) {
            Producto producto = item.getProducto();

            PedidoDetalle detalle = PedidoDetalle.builder()
                    .pedido(pedido)
                    .producto(producto)
                    .talla(item.getTalla())
                    .cantidad(item.getCantidad())
                    .build();

            pedido.getDetalles().add(detalle);

            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);

            total += producto.getPrecio() * item.getCantidad();
        }

        pedido.setTotal(total);
        pedidoRepository.save(pedido);

        carritoRepository.deleteAll(itemsCarrito);
    }
}
