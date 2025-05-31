package com.example.enlatados.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String id;
    private String nombre;
    private String apellidos;
    private String contrasena;

    @Override
    public String toString() {
        return String.format("%s %s (ID: %s)", nombre, apellidos, id);
    }
}

