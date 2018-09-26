package com.arensis.ann.neural;

import java.io.Serializable;

public class CapaEntrada extends Capa<NeuronaEntrada> implements Serializable {

    public CapaEntrada(int numeroNeuronas){
        this.setNeuronas(generarNeuronas(numeroNeuronas));
    }

    protected NeuronaEntrada[] generarNeuronas(int cantidad) {
        NeuronaEntrada[] neuronasEntradas = new NeuronaEntrada[cantidad];
        for (int i=0; i <cantidad; i++) {
            neuronasEntradas[i] = new NeuronaEntrada();
        }
        return neuronasEntradas;
    }
}
