package com.example.enlatados.edd;

import com.example.enlatados.model.Caja;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListaSimpleTest {
    private ListaSimple lista;

    @Before
    public void setUp() {
        lista = new ListaSimple();
    }

    @Test
    public void testInsertarYObtener() {
        Caja c = new Caja(1, java.time.LocalDateTime.now());
        lista.insertar(c);
        assertEquals(1, lista.contarCajas());
        assertTrue(lista.obtenerCajas().contains(c));
    }

    @Test
    public void testContarCajas() {
        assertEquals(0, lista.contarCajas());
        lista.insertar(new Caja(2, java.time.LocalDateTime.now()));
        assertEquals(1, lista.contarCajas());
    }
}
