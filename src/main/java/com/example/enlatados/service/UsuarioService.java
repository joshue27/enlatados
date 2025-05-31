package com.example.enlatados.service;
import com.example.enlatados.edd.Lista;
import com.example.enlatados.edd.Nodo;
import com.example.enlatados.model.Usuario;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final Lista usuarios = new Lista("usuarios");

    public void agregarUsuario(Usuario usuario) {
        usuarios.insertarAlFinal(usuario);
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        Nodo actual = usuarios.getIni(); // <- CORRECTO AQUÍ
        while (actual != null) {
            lista.add((Usuario) actual.getData());
            actual = actual.getSig();
        }
        return lista;
    }

    public boolean actualizar(String id, Usuario nuevo) {
        Nodo actual = usuarios.getIni();
        while (actual != null) {
            Usuario u = (Usuario) actual.getData();
            if (u.getId().equals(id)) {
                u.setNombre(nuevo.getNombre());
                u.setApellidos(nuevo.getApellidos());
                u.setContrasena(nuevo.getContrasena());
                return true;
            }
            actual = actual.getSig();
        }
        return false;
    }

    public boolean eliminarPorId(String id) {
        Lista temporal = new Lista("temporal");
        boolean eliminado = false;
        Nodo actual = usuarios.getIni();
        while (actual != null) {
            Usuario u = (Usuario) actual.getData();
            if (!u.getId().equals(id)) {
                temporal.insertarAlFinal(u);
            } else {
                eliminado = true;
            }
            actual = actual.getSig();
        }
        usuarios.setIni(temporal.getIni());
        usuarios.setFin(temporal.getFin());
        return eliminado;
    }

    public Lista getLista() {
        return usuarios;
    }
}
