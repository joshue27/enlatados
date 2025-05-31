package com.example.enlatados.edd;

import lombok.Getter;
import lombok.Setter;
import com.example.enlatados.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidos {

    @Getter @Setter
    private Nodo ini, fin;

    public boolean estaVacia() {
        return ini == null;
    }

    public void insertarAlFinal(Pedido p) {
        Nodo nuevo = new Nodo(p);
        if (estaVacia())
            ini = fin = nuevo;
        else
            fin = fin.sig = nuevo;
    }

    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        Nodo actual = ini;
        while (actual != null) {
            lista.add((Pedido) actual.getData());
            actual = actual.getSig();
        }
        return lista;
    }
    public boolean eliminarPorNumero(int numeroPedido) {
        if (estaVacia()) return false;

        ListaPedidos nueva = new ListaPedidos();
        boolean eliminado = false;
        Nodo actual = ini;

        while (actual != null) {
            Pedido p = (Pedido) actual.getData();
            if (p.getNumeroPedido() != numeroPedido) {
                nueva.insertarAlFinal(p);
            } else {
                eliminado = true;
            }
            actual = actual.getSig();
        }

        this.ini = nueva.getIni();
        this.fin = nueva.getFin();

        return eliminado;
    }

}
