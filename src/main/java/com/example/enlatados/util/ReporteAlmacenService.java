package com.example.enlatados.util;

import com.example.enlatados.edd.Pila;
import com.example.enlatados.model.Caja;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static guru.nidi.graphviz.model.Factory.*;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.Format;

public class ReporteAlmacenService {

    public static Graph generarGrafico(Pila pila) {
        Stack<Object> stack = pila.getPila(); // Obtener pila interna

        List<Node> nodos = new ArrayList<>();
        for (int i = stack.size() - 1; i >= 0; i--) {
            Caja caja = (Caja) stack.get(i);
            String etiqueta = "Caja #" + caja.getCorrelativo() + "\\n" + caja.getFechaIngreso().toLocalDate();
            Node nodo = node("n" + i).with("label", etiqueta);
            nodos.add(nodo);
        }

        Graph g = graph("almacen").directed();

        for (int j = 0; j < nodos.size(); j++) {
            g = g.with(nodos.get(j));
            if (j < nodos.size() - 1) {
                g = g.with(nodos.get(j).link(nodos.get(j + 1)));
            }
        }

        return g;
    }

    public static String generarReporte(Pila pila) throws IOException {
        Graph g = generarGrafico(pila);
        String ruta = "reporte_almacen.png";
        Graphviz.fromGraph(g).render(Format.PNG).toFile(new java.io.File(ruta));
        return ruta;
    }
}
