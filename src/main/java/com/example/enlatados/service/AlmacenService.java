package com.example.enlatados.service;

import com.example.enlatados.edd.Pila;
import com.example.enlatados.model.Caja;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlmacenService {

    private final Pila pilaCajas = new Pila();
    private int correlativoActual = 1;

    public void crearCajas(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Caja nueva = new Caja(correlativoActual++, LocalDateTime.now());
            pilaCajas.push(nueva);
        }
    }

    public Caja extraerCaja() {
        return (Caja) pilaCajas.pop();
    }

    public Caja verUltimaCaja() {
        return (Caja) pilaCajas.peek();
    }

    public List<Caja> listarCajas() {
        List<Caja> lista = new ArrayList<>();
        for (Object o : pilaCajas.getPila()) {
            lista.add((Caja) o);
        }
        return lista;
    }
    public void agregarCaja(Caja caja) {
        pilaCajas.push(caja);
    }


    public Pila getPila() {
        return pilaCajas;
    }
    public boolean eliminarCajaPorId(int correlativo) {
        return pilaCajas.eliminarPorId(correlativo);
    }

}
