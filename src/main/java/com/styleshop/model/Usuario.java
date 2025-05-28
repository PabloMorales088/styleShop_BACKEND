package com.styleshop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor vacío necesario para JPA
@AllArgsConstructor // Constructor con todos los campos
@Builder // Permite usar patrón builder para crear instancias de Usuario
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado por la base de datos
    private Long id;

    private String nombre; // Nombre del usuario

    @Column(unique = true, nullable = false)
    // El email debe ser único en la base de datos y no puede ser nulo
    private String email;

    private String password; // Contraseña encriptada (se asume que será cifrada con BCrypt u otro encoder)

    private boolean locked; // Indica si la cuenta está bloqueada

    private boolean disabled; // Indica si la cuenta está deshabilitada (por ejemplo, por inactividad o administración)
}
