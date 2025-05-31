package com.example.enlatados.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private int anio;

    @Override
    public String toString() {
        return placa + " - " + marca + " " + modelo + " (" + anio + ")";
    }
}
