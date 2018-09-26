package com.arensis.ann.neural;

public abstract class Capa<N extends Neurona> {
    private N[] neuronas;

    public Capa() {
    }

    public Capa(N[] neuronas) {
        this.neuronas = neuronas;
    }

    public N[] getNeuronas() {
        return neuronas;
    }

    public void setNeuronas(N[] neuronas) {
        this.neuronas = neuronas;
    }
}
