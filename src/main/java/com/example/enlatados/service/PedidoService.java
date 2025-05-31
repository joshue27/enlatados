package com.example.enlatados.service;

import com.example.enlatados.edd.ListaPedidos;
import com.example.enlatados.edd.ListaSimple;
import com.example.enlatados.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final ListaPedidos pedidos = new ListaPedidos();
    private int contadorPedidos = 1;

    @Autowired private ClienteService clienteService;
    @Autowired private RepartidorService repartidorService;
    @Autowired private VehiculoService vehiculoService;
    @Autowired private AlmacenService almacenService;

    public Pedido crearPedido(String cuiCliente, String deptoOrigen, String deptoDestino, int cantidadCajas) {
        Cliente cliente = clienteService.buscarPorCui(cuiCliente);
        if (cliente == null) throw new RuntimeException("Cliente no encontrado");

        Repartidor repartidor = repartidorService.asignarRepartidor();
        if (repartidor == null) throw new RuntimeException("No hay repartidores disponibles");

        Vehiculo vehiculo = vehiculoService.asignarVehiculo();
        if (vehiculo == null) throw new RuntimeException("No hay vehículos disponibles");

        ListaSimple listaCajas = new ListaSimple();
        for (int i = 0; i < cantidadCajas; i++) {
            Caja caja = almacenService.extraerCaja();
            if (caja != null) {
                listaCajas.insertar(caja);
            }
        }

        Pedido pedido = new Pedido(
                contadorPedidos++,
                deptoOrigen,
                deptoDestino,
                LocalDateTime.now(),
                cliente,
                repartidor,
                vehiculo,
                listaCajas,                          // ✅ aquí usas listaCajas, no cajas
                listaCajas.contarCajas(),            // ✅ número de cajas
                "Pendiente"
        );

        pedidos.insertarAlFinal(pedido);
        return pedido;
    }
    public boolean eliminarPedido(int numero) {
        return pedidos.eliminarPorNumero(numero);
    }

    public List<Pedido> listarPedidos() {
        return pedidos.listarTodos();
    }

    public void completarPedido(int numeroPedido) {
        for (Pedido p : pedidos.listarTodos()) {
            if (p.getNumeroPedido() == numeroPedido && p.getEstado().equals("Pendiente")) {
                p.setEstado("Completado");
                repartidorService.devolverRepartidor(p.getRepartidor());
                vehiculoService.devolverVehiculo(p.getVehiculo());
                break;
            }
        }
    }

    public ListaPedidos getListaPedidos() {
        return pedidos;
    }
}

