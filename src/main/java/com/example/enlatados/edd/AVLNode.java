package com.example.enlatados.edd;

import lombok.Getter;
import lombok.Setter;
import com.example.enlatados.model.Cliente;

@Getter
@Setter
public class AVLNode {
    private Cliente data;
    private AVLNode left;
    private AVLNode right;
    private int altura;

    public AVLNode(Cliente data) {
        this.data = data;
        this.altura = 1;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
