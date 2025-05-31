package com.example.enlatados.service;

import com.example.enlatados.edd.AVLTree;
import com.example.enlatados.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final AVLTree arbolClientes = new AVLTree();

    public void agregarCliente(Cliente cliente) {
        arbolClientes.insertar(cliente);
    }

    public Cliente buscarPorCui(String cui) {
        return arbolClientes.buscar(cui);
    }

    public List<Cliente> listarClientes() {
        return arbolClientes.inOrden();
    }

    public AVLTree getArbol() {
        return arbolClientes;
    }

    public boolean eliminarCliente(String cui) {
        return arbolClientes.eliminar(cui); // Retorna true si lo elimina, false si no existe
    }
    public boolean actualizarCliente(String cui, Cliente actualizado) {
        Cliente existente = arbolClientes.buscar(cui);
        if (existente != null) {
            arbolClientes.eliminar(cui); // Elimina el nodo antiguo
            arbolClientes.insertar(actualizado); // Inserta actualizado
            return true;
        }
        return false;
    }


}
