package com.example.enlatados.edd;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.example.enlatados.model.Pedido;

public class ListaPedidosTest {
    private ListaPedidos lista;

    @Before
    public void setUp() {
        lista = new ListaPedidos();
    }

    @Test
    public void testInsertarYListar() {
        Pedido p = new Pedido(1, "GT", "ES", java.time.LocalDateTime.now(), null, null, null, new ListaSimple(), 0, "Pendiente");
        lista.insertarAlFinal(p);
        assertEquals(1, lista.listarTodos().size());
    }

    @Test
    public void testEliminar() {
        Pedido p = new Pedido(2, "GT", "ES", java.time.LocalDateTime.now(), null, null, null, new ListaSimple(), 0, "Pendiente");
        lista.insertarAlFinal(p);
        assertTrue(lista.eliminarPorNumero(2));
    }
}
