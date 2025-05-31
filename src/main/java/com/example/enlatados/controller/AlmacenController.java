package com.example.enlatados.controller;

import com.example.enlatados.model.Caja;
import com.example.enlatados.util.CsvHelper;
import com.example.enlatados.util.GraphvizRenderService;
import guru.nidi.graphviz.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.enlatados.service.AlmacenService;
import com.example.enlatados.util.ReporteAlmacenService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/almacen")
public class AlmacenController {

    @Autowired
    private AlmacenService almacenService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearCajas(@RequestParam int cantidad) {
        almacenService.crearCajas(cantidad);
        return ResponseEntity.ok("Se crearon " + cantidad + " cajas.");
    }

    @GetMapping("/listar")
    public List<Caja> listar() {
        return almacenService.listarCajas();
    }

    @PostMapping("/extraer")
    public ResponseEntity<Caja> extraer() {
        Caja caja = almacenService.extraerCaja();
        if (caja == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(caja);
    }

    @GetMapping("/ver-ultima")
    public ResponseEntity<Caja> verUltima() {
        Caja caja = almacenService.verUltimaCaja();
        if (caja == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(caja);
    }

    @GetMapping("/reporte")
    public ResponseEntity<String> generarReporte() throws IOException {
        String ruta = ReporteAlmacenService.generarReporte(almacenService.getPila());
        return ResponseEntity.ok("Reporte generado en: " + ruta);
    }

    @GetMapping("/reporte-imagen")
    public ResponseEntity<byte[]> generarReporteImagen() throws IOException {
        Graph g = ReporteAlmacenService.generarGrafico(almacenService.getPila());
        byte[] imagen = GraphvizRenderService.renderToPng(g);
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(imagen);
    }
    @PostMapping("/cargar")
    public ResponseEntity<String> cargarCajasDesdeCsv(@RequestParam("file") MultipartFile file) {
        try {
            List<Caja> cajas = CsvHelper.parsearCajas(file);
            for (Caja c : cajas) {
                almacenService.agregarCaja(c);
            }
            return ResponseEntity.ok("Cajas cargadas correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error al procesar el archivo: " + e.getMessage());
        }
    }
    @DeleteMapping("/caja/{correlativo}")
    public ResponseEntity<String> eliminarCaja(@PathVariable int correlativo) {
        if (almacenService.eliminarCajaPorId(correlativo)) {
            return ResponseEntity.ok("Caja eliminada.");
        }
        return ResponseEntity.notFound().build();
    }


}
