package com.styleshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table( name = "Roles" )
public class Role {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String role;

    @JsonIgnore
    @OneToMany( mappedBy = "roleUser" ) //Nombre del atributo de @ManyToOne
    private List<Usuario> usuarios;

}
