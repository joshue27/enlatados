package com.example.enlatados.model;

import com.example.enlatados.edd.ListaSimple;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Pedido {
    private int numeroPedido;
    private String departamentoOrigen;
    private String departamentoDestino;
    private LocalDateTime fechaHoraInicio;
    private Cliente cliente;
    private Repartidor repartidor;
    private Vehiculo vehiculo;
    private ListaSimple cajas;     // <- Cambiado aquí
    private int numeroCajas;
    private String estado; // Pendiente o Completado
}
