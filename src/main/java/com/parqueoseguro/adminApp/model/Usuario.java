package com.parqueoseguro.adminApp.model;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id //--> Definir la Id como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Atributos para las columnas de la tabla usuario
    private String nombre;
    private String correo;
    private String rol;

    // Agrega constructor vacío (necesario para JPA)
    public Usuario() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}

