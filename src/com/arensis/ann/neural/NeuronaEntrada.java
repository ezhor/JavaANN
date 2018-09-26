package com.arensis.ann.neural;

public class NeuronaEntrada extends Neurona {
    private double entrada;

    public void setEntrada(double entrada) {
        this.entrada = entrada;
    }

    @Override
    public double propagar() {
        return entrada;
    }
}
