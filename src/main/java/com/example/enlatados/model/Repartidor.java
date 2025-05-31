package com.example.enlatados.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repartidor {
    private String cui;
    private String nombre;
    private String apellidos;
    private String licencia;
    private String telefono;

    @Override
    public String toString() {
        return nombre + " " + apellidos + " (" + cui + ")";
    }
}
