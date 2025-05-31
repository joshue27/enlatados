package com.example.enlatados.controller;

import com.example.enlatados.model.Cliente;
import com.example.enlatados.util.GraphvizRenderService;
import guru.nidi.graphviz.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.enlatados.service.ClienteService;
import com.example.enlatados.util.CsvHelper;
import com.example.enlatados.util.ReporteClienteService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cargar")
    public ResponseEntity<String> cargarDesdeCsv(@RequestParam("file") MultipartFile file) throws IOException {
        List<Cliente> clientes = CsvHelper.parsearClientes(file);
        for (Cliente c : clientes) {
            clienteService.agregarCliente(c);
        }
        return ResponseEntity.ok("Clientes cargados correctamente.");
    }

    @PostMapping
    public ResponseEntity<String> agregar(@RequestBody Cliente cliente) {
        clienteService.agregarCliente(cliente);
        return ResponseEntity.ok("Cliente agregado.");
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{cui}")
    public ResponseEntity<Cliente> buscar(@PathVariable String cui) {
        Cliente cliente = clienteService.buscarPorCui(cui);
        if (cliente != null)
            return ResponseEntity.ok(cliente);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/reporte")
    public ResponseEntity<String> generarReporte() throws IOException {
        String ruta = ReporteClienteService.generarReporte(clienteService.getArbol());
        return ResponseEntity.ok("Reporte generado en: " + ruta);
    }
    @GetMapping("/reporte-imagen")
    public ResponseEntity<byte[]> generarReporteImagen() throws IOException {
        Graph g = ReporteClienteService.generarGrafico(clienteService.getArbol());
        byte[] imagen = GraphvizRenderService.renderToPng(g);
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(imagen);
    }
    @DeleteMapping("/{cui}")
    public ResponseEntity<String> eliminar(@PathVariable String cui) {
        if (clienteService.eliminarCliente(cui)) {
            return ResponseEntity.ok("Cliente eliminado.");
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{cui}")
    public ResponseEntity<String> actualizar(@PathVariable String cui, @RequestBody Cliente cliente) {
        if (clienteService.actualizarCliente(cui, cliente)) {
            return ResponseEntity.ok("Cliente actualizado correctamente.");
        }
        return ResponseEntity.notFound().build();
    }


}
