package com.example.enlatados.util;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GraphvizRenderService {
    public static byte[] renderToPng(Graph graph) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Graphviz.fromGraph(graph).render(Format.PNG).toOutputStream(output);
        return output.toByteArray();
    }
}
