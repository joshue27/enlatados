package com.example.enlatados.service;

import com.example.enlatados.edd.Cola;
import com.example.enlatados.model.Vehiculo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoService {

    private final Cola colaVehiculos = new Cola();

    public void agregarVehiculo(Vehiculo v) {
        colaVehiculos.encolar(v);
    }

    public Vehiculo asignarVehiculo() {
        return (Vehiculo) colaVehiculos.desencolar();
    }

    public void devolverVehiculo(Vehiculo v) {
        colaVehiculos.encolar(v);
    }

    public boolean eliminarVehiculo(String placa) {
        return colaVehiculos.eliminarPorId(placa);
    }
    public boolean actualizarVehiculo(String placa, Vehiculo nuevo) {
        return colaVehiculos.actualizarPorId(placa, nuevo);
    }


    public List<Vehiculo> listar() {
        List<Vehiculo> lista = new ArrayList<>();
        for (Object o : colaVehiculos.toList()) {
            lista.add((Vehiculo) o);
        }
        return lista;
    }

    public Cola getCola() {
        return colaVehiculos;
    }
}
