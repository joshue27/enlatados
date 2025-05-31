package com.example.enlatados.controller;

import com.example.enlatados.model.Usuario;
import com.example.enlatados.util.GraphvizRenderService;
import guru.nidi.graphviz.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.enlatados.service.UsuarioService;
import com.example.enlatados.util.CsvHelper;
import com.example.enlatados.util.ReporteUsuarioService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cargar")
    public ResponseEntity<String> cargarDesdeCsv(@RequestParam("file") MultipartFile file) throws IOException {
        List<Usuario> usuarios = CsvHelper.parsearUsuarios(file);
        for (Usuario u : usuarios) {
            usuarioService.agregarUsuario(u);
        }
        return ResponseEntity.ok("Usuarios cargados correctamente.");
    }

    @PostMapping
    public ResponseEntity<String> agregar(@RequestBody Usuario usuario) {
        usuarioService.agregarUsuario(usuario);
        return ResponseEntity.ok("Usuario agregado.");
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable String id) {
        if (usuarioService.eliminarPorId(id)) {
            return ResponseEntity.ok("Usuario eliminado.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/reporte")
    public ResponseEntity<String> generarReporte() throws IOException {
        String ruta = ReporteUsuarioService.generarReporte(usuarioService.getLista());
        return ResponseEntity.ok("Reporte generado en: " + ruta);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable String id, @RequestBody Usuario actualizado) {
        if (usuarioService.actualizar(id, actualizado)) {
            return ResponseEntity.ok("Usuario actualizado.");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/reporte-imagen")
    public ResponseEntity<byte[]> generarReporteImagen() throws IOException {
        Graph g = ReporteUsuarioService.generarGrafico(usuarioService.getLista());
        byte[] imagen = GraphvizRenderService.renderToPng(g);
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(imagen);
    }


}
