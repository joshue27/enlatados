package com.example.enlatados.util;

import com.example.enlatados.edd.Lista;
import com.example.enlatados.edd.Nodo;
import com.example.enlatados.model.Usuario;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.*;

public class ReporteUsuarioService {

    public static Graph generarGrafico(Lista lista) {
        List<Node> nodos = new ArrayList<>();
        Nodo actual = lista.getIni();
        int i = 0;

        while (actual != null) {
            Usuario u = (Usuario) actual.getData();
            String etiqueta = String.format("ID: %s\\n%s %s", u.getId(), u.getNombre(), u.getApellidos());
            Node n = node("n" + i).with("label", etiqueta);
            nodos.add(n);
            actual = actual.getSig();
            i++;
        }

        Graph g = graph("usuarios").directed();

        for (int j = 0; j < nodos.size(); j++) {
            g = g.with(nodos.get(j));
            if (j < nodos.size() - 1) {
                g = g.with(nodos.get(j).link(nodos.get(j + 1)));
            }
        }

        return g;
    }

    public static String generarReporte(Lista lista) throws IOException {
        Graph g = generarGrafico(lista);
        String ruta = "reporte_usuarios.png";
        Graphviz.fromGraph(g).render(Format.PNG).toFile(new java.io.File(ruta));
        return ruta;
    }
}
