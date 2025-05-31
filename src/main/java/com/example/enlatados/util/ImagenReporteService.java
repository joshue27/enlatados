package com.example.enlatados.util;

import net.sourceforge.plantuml.SourceStringReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImagenReporteService {

    public static byte[] generarImagenDesdeCodigo(String codigoPlantUml) throws IOException {
        SourceStringReader reader = new SourceStringReader(codigoPlantUml);
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            // genera una sola imagen y la escribe al OutputStream
            reader.outputImage(os).getDescription();
            return os.toByteArray();
        }
    }
}

