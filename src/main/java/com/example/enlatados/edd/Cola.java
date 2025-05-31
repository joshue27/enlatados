package com.example.enlatados.edd;

import com.example.enlatados.model.Repartidor;
import com.example.enlatados.model.Vehiculo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Cola {
    @Getter
    private Nodo frente;
    @Getter
    private Nodo fin;

    public Cola() {
        frente = fin = null;
    }

    public boolean isEmpty() {
        return frente == null;
    }

    // Alias opcional
    public boolean estaVacia() {
        return isEmpty();
    }

    public void encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (isEmpty()) {
            frente = fin = nuevo;
        } else {
            fin.setSig(nuevo);
            fin = nuevo;
        }
    }

    public Object desencolar() {
        if (isEmpty()) return null;
        Object dato = frente.getData();
        frente = frente.getSig();
        if (frente == null) fin = null;
        return dato;
    }

    public Object peek() {
        return isEmpty() ? null : frente.getData();
    }

    public List<Object> toList() {
        List<Object> lista = new ArrayList<>();
        Nodo actual = frente;
        while (actual != null) {
            lista.add(actual.getData());
            actual = actual.getSig();
        }
        return lista;
    }

    public boolean eliminarPorId(String id) {
        if (isEmpty()) return false;

        Cola nueva = new Cola();
        boolean eliminado = false;

        while (!isEmpty()) {
            Object obj = desencolar();
            String comparar = "";

            if (obj instanceof Repartidor r) {
                comparar = r.getCui();
            } else if (obj instanceof Vehiculo v) {
                comparar = v.getPlaca();
            }

            if (!comparar.equals(id)) {
                nueva.encolar(obj);
            } else {
                eliminado = true;
            }
        }

        while (!nueva.isEmpty()) {
            encolar(nueva.desencolar());
        }

        return eliminado;
    }

    public boolean actualizarPorId(String id, Object nuevoDato) {
        if (isEmpty()) return false;

        Cola temporal = new Cola();
        boolean actualizado = false;

        while (!isEmpty()) {
            Object obj = desencolar();
            String comparar = "";

            if (obj instanceof Repartidor r) {
                comparar = r.getCui();
            } else if (obj instanceof Vehiculo v) {
                comparar = v.getPlaca();
            }

            if (comparar.equals(id)) {
                temporal.encolar(nuevoDato);
                actualizado = true;
            } else {
                temporal.encolar(obj);
            }
        }

        while (!temporal.isEmpty()) {
            encolar(temporal.desencolar());
        }

        return actualizado;
    }
}
