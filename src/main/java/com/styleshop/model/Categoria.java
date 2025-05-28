package com.styleshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categorias")
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor sin argumentos (requerido por JPA)
@AllArgsConstructor // Constructor con todos los campos
@Builder // Permite construir instancias usando el patrón builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID generado automáticamente por la base de datos
    private Long id;

    private String nombre; // Nombre de la categoría (ej. "Ropa", "Zapatos")

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    // Relación uno a muchos con Producto: una categoría puede tener muchos productos
    // mappedBy indica que la relación está mapeada por el campo "categoria" en la entidad Producto
    // cascade = ALL propaga todas las operaciones (persist, merge, remove, etc.) desde la categoría a sus productos
    private List<Producto> productos;
}
