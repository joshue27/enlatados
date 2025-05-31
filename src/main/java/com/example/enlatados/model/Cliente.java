package com.example.enlatados.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private String cui;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;

    @Override
    public String toString() {
        return nombre + " " + apellidos + " (" + cui + ")";
    }
}
