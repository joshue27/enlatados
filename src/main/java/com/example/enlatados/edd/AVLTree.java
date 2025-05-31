package com.example.enlatados.edd;

import com.example.enlatados.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    private AVLNode root;

    public void insertar(Cliente cliente) {
        root = insertarRec(root, cliente);
    }

    public Cliente buscar(String cui) {
        AVLNode actual = root;
        while (actual != null) {
            int cmp = cui.compareTo(actual.getData().getCui());
            if (cmp == 0) return actual.getData();
            actual = (cmp < 0) ? actual.getLeft() : actual.getRight();
        }
        return null;
    }

    public List<Cliente> inOrden() {
        List<Cliente> lista = new ArrayList<>();
        inOrdenRec(root, lista);
        return lista;
    }

    public AVLNode getRoot() {
        return root;
    }

    // --- MÉTODOS PRIVADOS ---

    private void inOrdenRec(AVLNode nodo, List<Cliente> lista) {
        if (nodo != null) {
            inOrdenRec(nodo.getLeft(), lista);
            lista.add(nodo.getData());
            inOrdenRec(nodo.getRight(), lista);
        }
    }

    private int altura(AVLNode nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }

    private int getBalance(AVLNode nodo) {
        return (nodo == null) ? 0 : altura(nodo.getLeft()) - altura(nodo.getRight());
    }

    private AVLNode insertarRec(AVLNode nodo, Cliente cliente) {
        if (nodo == null) return new AVLNode(cliente);

        int cmp = cliente.getCui().compareTo(nodo.getData().getCui());
        if (cmp < 0) nodo.setLeft(insertarRec(nodo.getLeft(), cliente));
        else if (cmp > 0) nodo.setRight(insertarRec(nodo.getRight(), cliente));
        else return nodo; // CUI duplicado, no se inserta

        // Actualizar altura
        nodo.setAltura(1 + Math.max(altura(nodo.getLeft()), altura(nodo.getRight())));

        // Balanceo
        int balance = getBalance(nodo);

        // Rotaciones
        if (balance > 1 && cliente.getCui().compareTo(nodo.getLeft().getData().getCui()) < 0)
            return rotarDerecha(nodo);
        if (balance < -1 && cliente.getCui().compareTo(nodo.getRight().getData().getCui()) > 0)
            return rotarIzquierda(nodo);
        if (balance > 1 && cliente.getCui().compareTo(nodo.getLeft().getData().getCui()) > 0) {
            nodo.setLeft(rotarIzquierda(nodo.getLeft()));
            return rotarDerecha(nodo);
        }
        if (balance < -1 && cliente.getCui().compareTo(nodo.getRight().getData().getCui()) < 0) {
            nodo.setRight(rotarDerecha(nodo.getRight()));
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private AVLNode rotarDerecha(AVLNode y) {
        AVLNode x = y.getLeft();
        AVLNode T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setAltura(1 + Math.max(altura(y.getLeft()), altura(y.getRight())));
        x.setAltura(1 + Math.max(altura(x.getLeft()), altura(x.getRight())));

        return x;
    }

    private AVLNode rotarIzquierda(AVLNode x) {
        AVLNode y = x.getRight();
        AVLNode T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setAltura(1 + Math.max(altura(x.getLeft()), altura(x.getRight())));
        y.setAltura(1 + Math.max(altura(y.getLeft()), altura(y.getRight())));

        return y;
    }
    public boolean eliminar(String cui) {
        if (buscar(cui) == null) return false;
        root = eliminarRec(root, cui);
        return true;
    }
    private AVLNode eliminarRec(AVLNode nodo, String cui) {
        if (nodo == null) return null;

        int cmp = cui.compareTo(nodo.getData().getCui());
        if (cmp < 0) {
            nodo.setLeft(eliminarRec(nodo.getLeft(), cui));
        } else if (cmp > 0) {
            nodo.setRight(eliminarRec(nodo.getRight(), cui));
        } else {
            // Nodo con uno o ningún hijo
            if (nodo.getLeft() == null) return nodo.getRight();
            else if (nodo.getRight() == null) return nodo.getLeft();

            // Nodo con dos hijos: obtener sucesor inorden
            AVLNode sucesor = obtenerMin(nodo.getRight());
            nodo.setData(sucesor.getData());
            nodo.setRight(eliminarRec(nodo.getRight(), sucesor.getData().getCui()));
        }

        // Actualizar altura
        nodo.setAltura(1 + Math.max(altura(nodo.getLeft()), altura(nodo.getRight())));

        // Balanceo
        int balance = getBalance(nodo);

        // Casos de rotación
        if (balance > 1 && getBalance(nodo.getLeft()) >= 0)
            return rotarDerecha(nodo);

        if (balance > 1 && getBalance(nodo.getLeft()) < 0) {
            nodo.setLeft(rotarIzquierda(nodo.getLeft()));
            return rotarDerecha(nodo);
        }

        if (balance < -1 && getBalance(nodo.getRight()) <= 0)
            return rotarIzquierda(nodo);

        if (balance < -1 && getBalance(nodo.getRight()) > 0) {
            nodo.setRight(rotarDerecha(nodo.getRight()));
            return rotarIzquierda(nodo);
        }

        return nodo;
    }
    private AVLNode obtenerMin(AVLNode nodo) {
        AVLNode actual = nodo;
        while (actual.getLeft() != null) {
            actual = actual.getLeft();
        }
        return actual;
    }

}
