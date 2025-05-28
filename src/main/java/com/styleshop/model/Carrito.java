package com.styleshop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carrito")
@Data // Genera getters, setters, equals, hashCode y toString automáticamente
@NoArgsConstructor // Constructor vacío requerido por JPA
@AllArgsConstructor // Constructor con todos los atributos
@Builder // Permite construir objetos usando el patrón builder
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental en base de datos
    private Long id;

    @ManyToOne // Relación muchos a uno con Usuario (varios carritos pueden pertenecer a un usuario)
    @JoinColumn(name = "usuario_id") // Clave foránea en la tabla carrito que apunta a usuario
    private Usuario usuario;

    @ManyToOne // Relación muchos a uno con Producto (varios carritos pueden contener el mismo producto)
    @JoinColumn(name = "producto_id") // Clave foránea en la tabla carrito que apunta a producto
    private Producto producto;

    private Integer cantidad; // Cantidad del producto agregado al carrito

    private String talla; // Talla del producto (relevante para ropa, por ejemplo)
}
