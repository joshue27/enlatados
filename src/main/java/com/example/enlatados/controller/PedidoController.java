package com.example.enlatados.controller;

import com.example.enlatados.model.Pedido;
import com.example.enlatados.util.GraphvizRenderService;
import guru.nidi.graphviz.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.enlatados.service.PedidoService;
import com.example.enlatados.util.ReportePedidoService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/crear")
    public ResponseEntity<Pedido> crear(
            @RequestParam String cuiCliente,
            @RequestParam String origen,
            @RequestParam String destino,
            @RequestParam int cantidadCajas
    ) {
        Pedido pedido = pedidoService.crearPedido(cuiCliente, origen, destino, cantidadCajas);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listarPedidos();
    }

    @PostMapping("/completar/{numero}")
    public ResponseEntity<String> completar(@PathVariable int numero) {
        pedidoService.completarPedido(numero);
        return ResponseEntity.ok("Pedido " + numero + " marcado como completado.");
    }

    @GetMapping("/reporte")
    public ResponseEntity<String> generarReporte() throws IOException {
        String ruta = ReportePedidoService.generarReporte(pedidoService.getListaPedidos());
        return ResponseEntity.ok("Reporte generado en: " + ruta);
    }

    @GetMapping("/{numero}/cajas")
    public ResponseEntity<?> verCajasDePedido(@PathVariable int numero) {
        for (Pedido p : pedidoService.listarPedidos()) {
            if (p.getNumeroPedido() == numero) {
                return ResponseEntity.ok(p.getCajas().obtenerCajas());
            }
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/reporte-imagen")
    public ResponseEntity<byte[]> generarReporteImagen() throws IOException {
        Graph g = ReportePedidoService.generarGrafico(pedidoService.getListaPedidos());
        byte[] imagen = GraphvizRenderService.renderToPng(g);
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(imagen);
    }
    @DeleteMapping("/{numero}")
    public ResponseEntity<String> eliminarPedido(@PathVariable int numero) {
        if (pedidoService.eliminarPedido(numero)) {
            return ResponseEntity.ok("Pedido eliminado.");
        }
        return ResponseEntity.notFound().build();
    }


}
