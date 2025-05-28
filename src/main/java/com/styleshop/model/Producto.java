package com.styleshop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor vacío requerido por JPA
@AllArgsConstructor // Constructor con todos los campos
@Builder // Permite usar patrón builder para construir objetos Producto
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental generado por la base de datos
    private Long id;

    private String nombre; // Nombre del producto

    @Column(columnDefinition = "TEXT") // Campo largo para descripciones extensas
    private String descripcion;

    private Double precio; // Precio del producto

    private String talla; // Talla del producto (puede ser opcional o variar por categoría)

    private Integer stock; // Cantidad disponible en inventario

    private String imagen; // Ruta o nombre de archivo de la imagen asociada

    @ManyToOne // Relación muchos a uno con la entidad Categoria
    @JoinColumn(name = "categoria_id") // Clave foránea que enlaza con la categoría
    private Categoria categoria;
}
