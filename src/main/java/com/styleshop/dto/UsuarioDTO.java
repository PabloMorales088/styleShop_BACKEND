package com.styleshop.dto;

public class UsuarioDTO {
    private String nombre;
    private String email;
    private String password; // ✅ CAMBIO DE 'contraseña' A 'password'

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
