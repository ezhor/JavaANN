package com.arensis.ann.neural;

public class Sinapsis implements Propagacion {
    private Propagacion neurona;
    private double peso;

    public Sinapsis(Propagacion neurona, double peso) {
        this.neurona = neurona;
        this.peso = peso;
    }

    public void setNeurona(Propagacion neurona) {
        this.neurona = neurona;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public double propagar() {
        return neurona.propagar() * peso;
    }
}
