package com.example.enlatados.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Caja {
    private int correlativo;
    private LocalDateTime fechaIngreso;

    @Override
    public String toString() {
        return "#" + correlativo + " (" + fechaIngreso.toLocalDate() + ")";
    }
}
