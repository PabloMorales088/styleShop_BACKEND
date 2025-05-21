package com.styleshop.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private String talla;
    private Long usuarioId;
}
