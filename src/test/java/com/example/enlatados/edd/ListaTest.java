package com.example.enlatados.edd;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListaTest {

    private Lista lista;

    @Before
    public void setUp() {
        lista = new Lista("Test");
    }

    @Test
    public void testInsertarAlFrente() {
        lista.insertarAlFrente("A");
        assertFalse(lista.estaVacia());
    }

    @Test
    public void testInsertarAlFinal() {
        lista.insertarAlFinal("B");
        assertFalse(lista.estaVacia());
    }

    @Test
    public void testEstaVacia() {
        assertTrue(lista.estaVacia());
        lista.insertarAlFrente("C");
        assertFalse(lista.estaVacia());
    }

    @Test
    public void testToStringNotEmpty() {
        lista.insertarAlFinal("D");
        assertNotNull(lista.toString());
    }
}
