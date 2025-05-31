package com.example.enlatados.controller;

import com.example.enlatados.model.Repartidor;
import com.example.enlatados.util.GraphvizRenderService;
import guru.nidi.graphviz.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.enlatados.service.RepartidorService;
import com.example.enlatados.util.CsvHelper;
import com.example.enlatados.util.ReporteRepartidorService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/repartidores")
public class RepartidorController {

    @Autowired
    private RepartidorService repartidorService;

    @PostMapping("/cargar")
    public ResponseEntity<String> cargarCsv(@RequestParam("file") MultipartFile file) throws IOException {
        List<Repartidor> lista = CsvHelper.parsearRepartidores(file);
        for (Repartidor r : lista) {
            repartidorService.agregarRepartidor(r);
        }
        return ResponseEntity.ok("Repartidores cargados correctamente.");
    }

    @PostMapping
    public ResponseEntity<String> agregar(@RequestBody Repartidor r) {
        repartidorService.agregarRepartidor(r);
        return ResponseEntity.ok("Repartidor agregado.");
    }

    @GetMapping
    public List<Repartidor> listar() {
        return repartidorService.listar();
    }

    @PostMapping("/asignar")
    public ResponseEntity<Repartidor> asignar() {
        Repartidor r = repartidorService.asignarRepartidor();
        if (r == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(r);
    }

    @PostMapping("/devolver")
    public ResponseEntity<String> devolver(@RequestBody Repartidor r) {
        repartidorService.devolverRepartidor(r);
        return ResponseEntity.ok("Repartidor devuelto a la cola.");
    }

    @GetMapping("/reporte")
    public ResponseEntity<String> generarReporte() throws IOException {
        String ruta = ReporteRepartidorService.generarReporte(repartidorService.getCola());
        return ResponseEntity.ok("Reporte generado en: " + ruta);
    }

    @GetMapping("/reporte-imagen")
    public ResponseEntity<byte[]> generarReporteImagen() throws IOException {
        Graph g = ReporteRepartidorService.generarGrafico(repartidorService.getCola());
        byte[] imagen = GraphvizRenderService.renderToPng(g);
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(imagen);
    }
    @DeleteMapping("/{cui}")
    public ResponseEntity<String> eliminar(@PathVariable String cui) {
        if (repartidorService.eliminarRepartidor(cui)) {
            return ResponseEntity.ok("Repartidor eliminado.");
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{cui}")
    public ResponseEntity<String> actualizar(@PathVariable String cui, @RequestBody Repartidor nuevo) {
        if (repartidorService.actualizarRepartidor(cui, nuevo)) {
            return ResponseEntity.ok("Repartidor actualizado.");
        }
        return ResponseEntity.notFound().build();
    }



}
