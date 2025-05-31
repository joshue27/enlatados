package com.example.enlatados.util;
import com.example.enlatados.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {

    public static List<Usuario> parsearUsuarios(MultipartFile file) {
        List<Usuario> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    Usuario usuario = new Usuario(partes[0], partes[1], partes[2], partes[3]);
                    lista.add(usuario);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al leer CSV de usuarios: " + e.getMessage());
        }
        return lista;
    }

    public static List<Cliente> parsearClientes(MultipartFile file) {
        List<Cliente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    Cliente cliente = new Cliente(partes[0], partes[1], partes[2], partes[3], partes[4]);
                    lista.add(cliente);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al leer CSV de clientes: " + e.getMessage());
        }
        return lista;
    }
    public static List<Repartidor> parsearRepartidores(MultipartFile file) {
        List<Repartidor> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    Repartidor r = new Repartidor(partes[0], partes[1], partes[2], partes[3], partes[4]);
                    lista.add(r);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al leer CSV de repartidores: " + e.getMessage());
        }
        return lista;
    }

    public static List<Vehiculo> parsearVehiculos(MultipartFile file) {
        List<Vehiculo> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    Vehiculo v = new Vehiculo(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3],
                            Integer.parseInt(partes[4])
                    );
                    lista.add(v);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al leer CSV de vehículos: " + e.getMessage());
        }
        return lista;
    }

    public static List<Caja> parsearCajas(MultipartFile file) throws IOException {
        List<Caja> cajas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(";");
            int correlativo = Integer.parseInt(partes[0]);
            LocalDateTime fecha = LocalDateTime.parse(partes[1]); // formato ISO
            cajas.add(new Caja(correlativo, fecha));
        }
        return cajas;
    }



}
