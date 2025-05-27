package com.styleshop.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private String nombre;
    private String email;
    private String password;
}