package com.example.enlatados.util;

import com.example.enlatados.edd.ListaPedidos;
import com.example.enlatados.edd.Nodo;
import com.example.enlatados.model.Pedido;
import com.example.enlatados.model.Cliente;
import com.example.enlatados.model.Repartidor;
import com.example.enlatados.model.Vehiculo;

import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.*;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.Format;

public class ReportePedidoService {

    public static Graph generarGrafico(ListaPedidos pedidos) {
        Nodo actual = pedidos.getIni();
        List<Node> nodos = new ArrayList<>();
        int i = 0;

        while (actual != null) {
            Pedido p = (Pedido) actual.getData();
            Cliente c = p.getCliente();
            Repartidor r = p.getRepartidor();
            Vehiculo v = p.getVehiculo();

            String etiqueta = String.format(
                    "Pedido #%d\\nEstado: %s\\nCliente: %s %s\\nPiloto: %s %s\\nVehículo: %s (%s)\\nOrigen: %s\\nDestino: %s\\nCajas: %d",
                    p.getNumeroPedido(),
                    p.getEstado(),
                    c.getNombre(), c.getApellidos(),
                    r.getNombre(), r.getApellidos(),
                    v.getPlaca(), v.getMarca(),
                    p.getDepartamentoOrigen(),
                    p.getDepartamentoDestino(),
                    p.getNumeroCajas()
            );

            Node nodo = node("n" + i).with("label", etiqueta);
            nodos.add(nodo);
            actual = actual.getSig();
            i++;
        }

        Graph g = graph("pedidos").directed();

        for (int j = 0; j < nodos.size(); j++) {
            g = g.with(nodos.get(j));
            if (j < nodos.size() - 1) {
                g = g.with(nodos.get(j).link(nodos.get(j + 1)));
            }
        }

        return g;
    }

    public static String generarReporte(ListaPedidos pedidos) throws IOException {
        Graph g = generarGrafico(pedidos);
        String ruta = "reporte_pedidos.png";
        Graphviz.fromGraph(g).render(Format.PNG).toFile(new java.io.File(ruta));
        return ruta;
    }
}
