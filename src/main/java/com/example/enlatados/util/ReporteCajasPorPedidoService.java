package com.example.enlatados.util;

import com.example.enlatados.edd.ListaSimple;
import com.example.enlatados.edd.Nodo;
import com.example.enlatados.model.Caja;

import java.io.FileWriter;
import java.io.IOException;

public class ReporteCajasPorPedidoService {

    public static String generarReporte(ListaSimple lista, int numeroPedido) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("@startuml\n");
        sb.append("digraph cajas_pedido_" + numeroPedido + " {\n");

        Nodo actual = lista.getInicio();
        int contador = 0;
        while (actual != null) {
            Caja caja = (Caja) actual.getData();
            sb.append(String.format("n%d [label=\"%s\"];\n", contador, caja.toString().replace("\"", "")));
            if (actual.getSig() != null) {
                sb.append(String.format("n%d -> n%d;\n", contador, contador + 1));
            }
            actual = actual.getSig();
            contador++;
        }

        sb.append("}\n@enduml");

        String ruta = "reporte_cajas_pedido_" + numeroPedido + ".puml";
        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write(sb.toString());
        }
        return ruta;
    }
}
