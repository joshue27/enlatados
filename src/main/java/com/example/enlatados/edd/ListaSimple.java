package com.example.enlatados.edd;

import lombok.Getter;
import com.example.enlatados.model.Caja;

import java.util.ArrayList;
import java.util.List;

public class ListaSimple {

    @Getter
    private Nodo inicio;

    public void insertar(Caja caja) {
        Nodo nuevo = new Nodo(caja);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo actual = inicio;
            while (actual.sig != null) {
                actual = actual.sig;
            }
            actual.sig = nuevo;
        }
    }

    public List<Caja> obtenerCajas() {
        List<Caja> lista = new ArrayList<>();
        Nodo actual = inicio;
        while (actual != null) {
            lista.add((Caja) actual.data);
            actual = actual.sig;
        }
        return lista;
    }

    public int contarCajas() {
        int count = 0;
        Nodo actual = inicio;
        while (actual != null) {
            count++;
            actual = actual.sig;
        }
        return count;
    }
}

