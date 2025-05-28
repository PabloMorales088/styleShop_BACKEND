package com.styleshop.mapper;

import com.styleshop.dto.PedidoDTO;
import com.styleshop.model.Pedido;
import com.styleshop.model.PedidoDetalle;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    // Convierte una entidad Pedido a su correspondiente DTO, incluyendo los detalles del pedido
    public static PedidoDTO toDTO(Pedido pedido) {
        List<PedidoDTO.DetalleDTO> detalles = pedido.getDetalles().stream()
                .map(PedidoMapper::mapDetalle) // Mapea cada detalle a su DTO
                .collect(Collectors.toList());

        return new PedidoDTO(
                pedido.getId(), // ID del pedido
                pedido.getTotal(), // Total del pedido
                pedido.getEstado().name(), // Convierte enum Estado a String
                pedido.getFecha(), // Fecha del pedido
                detalles // Lista de detalles mapeados
        );
    }

    // Método auxiliar para mapear un PedidoDetalle a su DTO anidado en PedidoDTO
    private static PedidoDTO.DetalleDTO mapDetalle(PedidoDetalle detalle) {
        return new PedidoDTO.DetalleDTO(
                detalle.getProducto().getNombre(), // Nombre del producto
                detalle.getProducto().getImagen(), // Imagen del producto
                detalle.getProducto().getPrecio(), // Precio del producto
                detalle.getTalla(), // Talla seleccionada
                detalle.getCantidad() // Cantidad pedida
        );
    }
}
