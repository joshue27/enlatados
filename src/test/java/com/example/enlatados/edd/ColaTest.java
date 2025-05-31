package com.example.enlatados.edd;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColaTest {
    private Cola cola;

    @Before
    public void setUp() {
        cola = new Cola();
    }

    @Test
    public void testEncolarDesencolar() {
        cola.encolar("A");
        assertEquals("A", cola.desencolar());
    }

    @Test
    public void testPeek() {
        cola.encolar("Inicio");
        assertEquals("Inicio", cola.peek());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(cola.isEmpty());
        cola.encolar("Dato");
        assertFalse(cola.isEmpty());
    }
}
