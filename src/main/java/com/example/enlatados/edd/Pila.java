package com.example.enlatados.edd;

import java.util.Stack;

public class Pila {
    private final Stack<Object> pila = new Stack<>();

    public void push(Object dato) {
        pila.push(dato);
    }

    public Object pop() {
        return pila.isEmpty() ? null : pila.pop();
    }

    public Object peek() {
        return pila.isEmpty() ? null : pila.peek();
    }

    public boolean isEmpty() {
        return pila.isEmpty();
    }

    public Stack<Object> getPila() {
        return pila;
    }
    public boolean eliminarPorId(int correlativo) {
        Stack<Object> temporal = new Stack<>();
        boolean eliminado = false;

        // Extraer todos los elementos mientras buscamos el que se eliminará
        while (!pila.isEmpty()) {
            Object obj = pila.pop();
            if (obj instanceof com.example.enlatados.model.Caja caja) {
                if (caja.getCorrelativo() != correlativo) {
                    temporal.push(caja);
                } else {
                    eliminado = true;
                }
            } else {
                temporal.push(obj); // si no es Caja, mantenerlo igual
            }
        }

        // Restaurar la pila
        while (!temporal.isEmpty()) {
            pila.push(temporal.pop());
        }

        return eliminado;
    }

}
