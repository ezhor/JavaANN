package com.arensis.ann.trainning;

public class Entrenamiento<S>{
    private double[] entradas;
    private S salida;

    public Entrenamiento() {
    }

    public Entrenamiento(double[] entradas, S salida) {
        this.entradas = entradas;
        this.salida = salida;
    }

    public double[] getEntradas() {
        return entradas;
    }

    public void setEntradas(double[] entradas) {
        this.entradas = entradas;
    }

    public S getSalida() {
        return salida;
    }

    public void setSalida(S salida) {
        this.salida = salida;
    }
}
