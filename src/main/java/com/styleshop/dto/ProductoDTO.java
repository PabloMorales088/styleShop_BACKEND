package com.styleshop.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String talla;
    private Integer stock;
    private String imagen;
    private Long categoriaId;
}
