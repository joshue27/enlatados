package com.example.enlatados.edd;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodoTest {

    @Test
    public void testNodoConstructorAndGetters() {
        Nodo nodo = new Nodo("TestData");
        assertEquals("TestData", nodo.getData());
        assertNull(nodo.getSig());
    }

    @Test
    public void testSetSig() {
        Nodo nodo1 = new Nodo("First");
        Nodo nodo2 = new Nodo("Second");
        nodo1.setSig(nodo2);
        assertEquals(nodo2, nodo1.getSig());
    }

    @Test
    public void testToString() {
        Nodo nodo = new Nodo("Data");
        assertTrue(nodo.toString().contains("Data"));
    }
}
