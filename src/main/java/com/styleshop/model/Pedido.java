package com.styleshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor vacío requerido por JPA
@AllArgsConstructor // Constructor con todos los campos
@Builder // Permite usar patrón builder para crear objetos
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado por la base de datos
    private Long id;

    @ManyToOne // Relación muchos a uno: muchos pedidos pueden pertenecer a un mismo usuario
    @JoinColumn(name = "usuario_id") // Clave foránea que referencia al usuario
    private Usuario usuario;

    private Double total; // Total monetario del pedido

    @Enumerated(EnumType.STRING) // Enum guardado como cadena (no como ordinal)
    private Estado estado; // Estado del pedido: Procesando, Enviado o Entregado

    private LocalDateTime fecha; // Fecha y hora en que se realizó el pedido

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // Relación uno a muchos con los detalles del pedido
    // mappedBy indica que el campo "pedido" en PedidoDetalle es el dueño de la relación
    // cascade ALL propaga operaciones al detalle (guardar, eliminar, etc.)
    // fetch EAGER carga los detalles inmediatamente junto con el pedido
    private List<PedidoDetalle> detalles = new ArrayList<>();

    // Enum interno que representa los posibles estados del pedido
    public enum Estado {
        Procesando, Enviado, Entregado
    }
}
