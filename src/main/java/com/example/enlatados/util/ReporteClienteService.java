package com.example.enlatados.util;

import com.example.enlatados.edd.AVLNode;
import com.example.enlatados.edd.AVLTree;
import com.example.enlatados.model.Cliente;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.IOException;

import static guru.nidi.graphviz.model.Factory.*;

public class ReporteClienteService {

    public static Graph generarGrafico(AVLTree arbol) {
        if (arbol.getRoot() == null) {
            return graph("clientes").directed().with();
        }

        return graph("clientes").directed().with(generarSubarbol(arbol.getRoot()));
    }

    private static Node generarSubarbol(AVLNode nodo) {
        if (nodo == null) return null;

        Cliente cliente = nodo.getData();
        String label = String.format(
                "CUI: %s\\n%s %s\\nDir: %s",
                cliente.getCui(),
                cliente.getNombre(),
                cliente.getApellidos(),
                cliente.getDireccion()
        );

        Node actual = node(cliente.getCui()).with("label", label);

        if (nodo.getLeft() != null) {
            Node izquierdo = generarSubarbol(nodo.getLeft());
            actual = actual.link(izquierdo);
        }

        if (nodo.getRight() != null) {
            Node derecho = generarSubarbol(nodo.getRight());
            actual = actual.link(derecho);
        }

        return actual;
    }

    public static String generarReporte(AVLTree arbol) throws IOException {
        Graph g = generarGrafico(arbol);
        String ruta = "reporte_clientes.png";
        Graphviz.fromGraph(g).render(Format.PNG).toFile(new java.io.File(ruta));
        return ruta;
    }
}
