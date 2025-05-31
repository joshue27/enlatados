package com.example.enlatados.edd;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PilaTest {
    private Pila pila;

    @Before
    public void setUp() {
        pila = new Pila();
    }

    @Test
    public void testPushAndPop() {
        pila.push("Elemento");
        assertEquals("Elemento", pila.pop());
    }

    @Test
    public void testPeek() {
        pila.push("Topo");
        assertEquals("Topo", pila.peek());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(pila.isEmpty());
        pila.push("X");
        assertFalse(pila.isEmpty());
    }
}
