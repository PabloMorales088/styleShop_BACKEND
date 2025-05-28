package com.styleshop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pedido_detalles")
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor sin argumentos, requerido por JPA
@AllArgsConstructor // Constructor con todos los campos
@Builder // Permite la construcción de objetos con el patrón builder
public class PedidoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado por la base de datos
    private Long id;

    @ManyToOne // Relación muchos a uno con Pedido
    @JoinColumn(name = "pedido_id") // Clave foránea que enlaza con la tabla de pedidos
    private Pedido pedido;

    @ManyToOne // Relación muchos a uno con Producto
    @JoinColumn(name = "producto_id") // Clave foránea que enlaza con la tabla de productos
    private Producto producto;

    private String talla; // Talla seleccionada del producto en este detalle

    private Integer cantidad; // Cantidad de este producto específica en el pedido
}
