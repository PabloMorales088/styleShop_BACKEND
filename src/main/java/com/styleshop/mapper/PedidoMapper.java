package com.styleshop.mapper;

import com.styleshop.dto.PedidoDTO;
import com.styleshop.model.Pedido;
import com.styleshop.model.PedidoDetalle;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoDTO toDTO(Pedido pedido) {
        List<PedidoDTO.DetalleDTO> detalles = pedido.getDetalles().stream()
                .map(PedidoMapper::mapDetalle)
                .collect(Collectors.toList());

        return new PedidoDTO(
                pedido.getId(),
                pedido.getTotal(),
                pedido.getEstado().name(),
                pedido.getFecha(),
                detalles
        );
    }

    private static PedidoDTO.DetalleDTO mapDetalle(PedidoDetalle detalle) {
        return new PedidoDTO.DetalleDTO(
                detalle.getProducto().getNombre(),
                detalle.getProducto().getImagen(),
                detalle.getProducto().getPrecio(),
                detalle.getTalla(),
                detalle.getCantidad()
        );
    }
}
