package com.example.enlatados.service;

import com.example.enlatados.edd.Cola;
import com.example.enlatados.model.Repartidor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepartidorService {

    private final Cola colaRepartidores = new Cola();

    public void agregarRepartidor(Repartidor r) {
        colaRepartidores.encolar(r);
    }

    public Repartidor asignarRepartidor() {
        return (Repartidor) colaRepartidores.desencolar();
    }

    public void devolverRepartidor(Repartidor r) {
        colaRepartidores.encolar(r);
    }

    public boolean eliminarRepartidor(String cui) {
        return colaRepartidores.eliminarPorId(cui); // ✅ nombre corregido
    }
    public boolean actualizarRepartidor(String cui, Repartidor nuevo) {
        return colaRepartidores.actualizarPorId(cui, nuevo);
    }


    public List<Repartidor> listar() {
        List<Repartidor> lista = new ArrayList<>();
        for (Object o : colaRepartidores.toList()) {
            lista.add((Repartidor) o);
        }
        return lista;
    }

    public Cola getCola() {
        return colaRepartidores;
    }
}
