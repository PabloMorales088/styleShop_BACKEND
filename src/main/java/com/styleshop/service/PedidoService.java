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
@RequiredArgsConstructor // Genera constructor con dependencias inyectadas
public class PedidoService {

    private final UsuarioRepository usuarioRepository;
    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;

    @Transactional // Asegura que todo el proceso se ejecute como una transacción atómica
    public void confirmarPedido(Long usuarioId) {
        // 1. Obtener el usuario
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        // 2. Obtener ítems del carrito
        List<Carrito> itemsCarrito = carritoRepository.findByUsuarioId(usuarioId);
        if (itemsCarrito.isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        // 3. Validar stock disponible para cada producto en el carrito
        for (Carrito item : itemsCarrito) {
            Producto producto = item.getProducto();
            if (producto.getStock() < item.getCantidad()) {
                throw new IllegalStateException("Stock insuficiente para " + producto.getNombre());
            }
        }

        // 4. Crear el pedido vacío con estado inicial
        Pedido pedido = Pedido.builder()
                .usuario(usuario)
                .fecha(LocalDateTime.now())
                .estado(Pedido.Estado.Procesando)
                .total(0.0)
                .detalles(new ArrayList<>())
                .build();

        double total = 0;

        // 5. Construir los detalles del pedido y descontar stock
        for (Carrito item : itemsCarrito) {
            Producto producto = item.getProducto();

            PedidoDetalle detalle = PedidoDetalle.builder()
                    .pedido(pedido) // Asociar con el pedido actual
                    .producto(producto)
                    .talla(item.getTalla())
                    .cantidad(item.getCantidad())
                    .build();

            pedido.getDetalles().add(detalle); // Agregar detalle al pedido

            producto.setStock(producto.getStock() - item.getCantidad()); // Descontar del stock
            productoRepository.save(producto); // Guardar cambios en el producto

            total += producto.getPrecio() * item.getCantidad(); // Acumular total del pedido
        }

        // 6. Establecer el total del pedido y guardarlo
        pedido.setTotal(total);
        pedidoRepository.save(pedido);

        // 7. Vaciar el carrito del usuario
        carritoRepository.deleteAll(itemsCarrito);
    }
}
