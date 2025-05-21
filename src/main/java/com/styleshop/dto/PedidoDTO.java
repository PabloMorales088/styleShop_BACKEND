package com.styleshop.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private Double total;
    private String estado;
    private LocalDateTime fecha;
    private List<DetalleDTO> detalles;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetalleDTO {
        private String nombreProducto;
        private String imagen;
        private Double precio;
        private String talla;
        private Integer cantidad;
    }
}
