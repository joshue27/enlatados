package com.example.enlatados.util;

import com.example.enlatados.edd.Cola;
import com.example.enlatados.edd.Nodo;
import com.example.enlatados.model.Repartidor;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.*;

public class ReporteRepartidorService {

    public static Graph generarGrafico(Cola cola) {
        Nodo actual = cola.getFrente();
        List<Node> nodos = new ArrayList<>();
        int i = 0;

        while (actual != null) {
            Repartidor r = (Repartidor) actual.getData();
            String label = String.format(
                    "CUI: %s\\n%s %s\\nLicencia: %s",
                    r.getCui(),
                    r.getNombre(),
                    r.getApellidos(),
                    r.getLicencia()
            );
            Node nodo = node("n" + i).with("label", label);
            nodos.add(nodo);
            actual = actual.getSig();
            i++;
        }

        Graph g = graph("repartidores").directed();
        for (int j = 0; j < nodos.size(); j++) {
            g = g.with(nodos.get(j));
            if (j < nodos.size() - 1) {
                g = g.with(nodos.get(j).link(nodos.get(j + 1)));
            }
        }

        return g;
    }

    public static String generarReporte(Cola cola) throws IOException {
        Graph g = generarGrafico(cola);
        String ruta = "reporte_repartidores.png";
        Graphviz.fromGraph(g).render(Format.PNG).toFile(new java.io.File(ruta));
        return ruta;
    }
}
