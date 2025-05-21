package com.styleshop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private boolean locked;

    private boolean disabled;

    @Column( name = "role_id" )
    private Long roleId;

    @ManyToOne //nombre en BD de la FK
    @JoinColumn( name = "role_id", insertable = false, updatable = false )
    private Role roleUser;
}
