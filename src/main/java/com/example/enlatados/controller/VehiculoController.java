package com.example.enlatados.controller;

import com.example.enlatados.model.Vehiculo;
import com.example.enlatados.util.GraphvizRenderService;
import guru.nidi.graphviz.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.enlatados.service.VehiculoService;
import com.example.enlatados.util.CsvHelper;
import com.example.enlatados.util.ReporteVehiculoService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/cargar")
    public ResponseEntity<String> cargarCsv(@RequestParam("file") MultipartFile file) throws IOException {
        List<Vehiculo> lista = CsvHelper.parsearVehiculos(file);
        for (Vehiculo v : lista) {
            vehiculoService.agregarVehiculo(v);
        }
        return ResponseEntity.ok("Vehículos cargados correctamente.");
    }

    @PostMapping
    public ResponseEntity<String> agregar(@RequestBody Vehiculo v) {
        vehiculoService.agregarVehiculo(v);
        return ResponseEntity.ok("Vehículo agregado.");
    }

    @GetMapping
    public List<Vehiculo> listar() {
        return vehiculoService.listar();
    }

    @PostMapping("/asignar")
    public ResponseEntity<Vehiculo> asignar() {
        Vehiculo v = vehiculoService.asignarVehiculo();
        if (v == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(v);
    }

    @PostMapping("/devolver")
    public ResponseEntity<String> devolver(@RequestBody Vehiculo v) {
        vehiculoService.devolverVehiculo(v);
        return ResponseEntity.ok("Vehículo devuelto a la cola.");
    }

    @GetMapping("/reporte")
    public ResponseEntity<String> generarReporte() throws IOException {
        String ruta = ReporteVehiculoService.generarReporte(vehiculoService.getCola());
        return ResponseEntity.ok("Reporte generado en: " + ruta);
    }
    @GetMapping("/reporte-imagen")
    public ResponseEntity<byte[]> generarReporteImagen() throws IOException {
        Graph g = ReporteVehiculoService.generarGrafico(vehiculoService.getCola());
        byte[] imagen = GraphvizRenderService.renderToPng(g);
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(imagen);
    }
    @DeleteMapping("/{placa}")
    public ResponseEntity<String> eliminar(@PathVariable String placa) {
        if (vehiculoService.eliminarVehiculo(placa)) {
            return ResponseEntity.ok("Vehículo eliminado.");
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{placa}")
    public ResponseEntity<String> actualizar(@PathVariable String placa, @RequestBody Vehiculo nuevo) {
        if (vehiculoService.actualizarVehiculo(placa, nuevo)) {
            return ResponseEntity.ok("Vehículo actualizado.");
        }
        return ResponseEntity.notFound().build();
    }


}
