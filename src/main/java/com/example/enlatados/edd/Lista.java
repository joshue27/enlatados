package com.example.enlatados.edd;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lista {
    private Nodo ini, fin;
    private String nombre;

    public Lista(String nombre) {
        ini = fin = null;
        this.nombre = nombre;
    }

    public Lista() {
        this("mi-lista");
    }

    public boolean estaVacia() {
        return ini == null;
    }

    public void insertarAlFrente(Object d) {
        if (estaVacia())
            ini = fin = new Nodo(d);
        else
            ini = new Nodo(d, ini);
    }

    public void insertarAlFinal(Object d) {
        if (estaVacia())
            ini = fin = new Nodo(d);
        else
            fin = fin.sig = new Nodo(d);
    }

    public String imprimir() {
        StringBuilder str = new StringBuilder();
        if (estaVacia())
            return String.format("%s vacía\n", nombre);
        Nodo actual = ini;
        str.append(String.format("La %s es: ", nombre));
        while (actual != null) {
            str.append(String.format("%s ", actual.data));
            actual = actual.sig;
        }
        return str.toString().trim();
    }

    @Override
    public String toString() {
        return "Lista{" + imprimir() + '}';
    }
}
