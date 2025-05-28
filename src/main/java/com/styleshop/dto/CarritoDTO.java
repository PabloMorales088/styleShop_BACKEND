package com.styleshop.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

//"Entidad" de la "Entidad" de CARRITO con los atributos especificos que queremos devolver en casos detrminados
public class CarritoDTO {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private String talla;
    private Long usuarioId;
}
