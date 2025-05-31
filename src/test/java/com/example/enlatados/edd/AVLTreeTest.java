package com.example.enlatados.edd;

import com.example.enlatados.model.Cliente;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AVLTreeTest {
    private AVLTree arbol;

    @Before
    public void setUp() {
        arbol = new AVLTree();
    }

    @Test
    public void testInsertarYBuscar() {
        Cliente c = new Cliente("123", "Luis", "Perez", "12345678", "Guatemala");
        arbol.insertar(c);
        assertNotNull(arbol.buscar("123"));
    }

    @Test
    public void testEliminar() {
        Cliente c = new Cliente("124", "Ana", "Lopez", "87654321", "Mixco");
        arbol.insertar(c);
        assertTrue(arbol.eliminar("124"));
    }

    @Test
    public void testInOrden() {
        arbol.insertar(new Cliente("111", "A", "A", "1", "A"));
        arbol.insertar(new Cliente("222", "B", "B", "2", "B"));
        assertEquals(2, arbol.inOrden().size());
    }
}
