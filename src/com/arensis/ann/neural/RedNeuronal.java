package com.arensis.ann.neural;

import com.arensis.ann.processing.PostProcesador;

import java.io.Serializable;

public class RedNeuronal<S> implements Serializable {
    private CapaEntrada capaEntrada;
    private CapaOculta[] capasOcultas;
    private CapaSalida<S> capaSalida;
    private double bias;

    protected RedNeuronal() {
    }

    public RedNeuronal(int numeroNeuronasEntrada, int numeroCapasOcultas, int numeroNeuronasPorCapaOculta, int numeroNeuronasSalida, double bias, PostProcesador postProcesador){
        this.capaEntrada = new CapaEntrada(numeroNeuronasEntrada);
        this.capasOcultas = generarCapasOcultas(numeroCapasOcultas, numeroNeuronasPorCapaOculta, this.capaEntrada, bias);
        this.capaSalida = new CapaSalida<>(numeroNeuronasSalida, this.capasOcultas[this.capasOcultas.length-1], bias, postProcesador);
        this.bias = bias;
    }

    public RedNeuronal(CapaEntrada capaEntrada, CapaOculta[] capasOcultas, CapaSalida<S> capaSalida) {
        this.capaEntrada = capaEntrada;
        this.capasOcultas = capasOcultas;
        this.capaSalida = capaSalida;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(int bias) {
        this.bias = bias;
    }

    public CapaEntrada getCapaEntrada() {
        return capaEntrada;
    }

    public void setCapaEntrada(CapaEntrada capaEntrada) {
        this.capaEntrada = capaEntrada;
    }

    public CapaOculta[] getCapasOcultas() {
        return capasOcultas;
    }

    public void setCapasOcultas(CapaOculta[] capasOcultas) {
        this.capasOcultas = capasOcultas;
    }

    public CapaSalida getCapaSalida() {
        return capaSalida;
    }

    public void setCapaSalida(CapaSalida<S> capaSalida) {
        this.capaSalida = capaSalida;
    }

    private CapaOculta[] generarCapasOcultas(int numeroCapas, int neuronasPorCapa, CapaEntrada capaEntrada, double bias){
        CapaOculta[] capasOcultas = new CapaOculta[numeroCapas];
        Capa capaPrevia = capaEntrada;
        for (int i=0; i <numeroCapas; i++) {
            capasOcultas[i] = new CapaOculta(neuronasPorCapa, capaPrevia, bias);
            capaPrevia = capasOcultas[i];
        }
        return capasOcultas;
    }

    public S calcularSalidaProcesada(double... entradas) throws ExcepcionNeuronal {
        if(entradas.length == this.capaEntrada.getNeuronas().length){
            for (int i=0; i<entradas.length; i++) {
                this.capaEntrada.getNeuronas()[i].setEntrada(entradas[i]);
            }
        }else{
            throw new ExcepcionNeuronal("El número de entradas no es el mismo que el número de neuronas de entrada.");
        }
        return capaSalida.getSalidaProcesada();
    }

    public double[] calcularSalida(double... entradas) throws ExcepcionNeuronal {
        double[] salida;

        if(entradas.length == this.capaEntrada.getNeuronas().length){
            for (int i=0; i<entradas.length; i++) {
                this.capaEntrada.getNeuronas()[i].setEntrada(entradas[i]);
            }
            salida = capaSalida.getSalidaSinProcesar();
        }else{
            throw new ExcepcionNeuronal("El número de entradas no es el mismo que el número de neuronas de entrada.");
        }
        return salida;
    }

    public double calcularError(S salidaEsperada, double... entradas) throws ExcepcionNeuronal {
        return capaSalida.calcularError(salidaEsperada, calcularSalida(entradas));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(capaEntrada.getNeuronas().length);
        builder.append("/");

        for (int i = 0; i < capasOcultas.length; i++) {
            if(i>0){
                builder = builder.append("/");
            }
            builder.append(capasOcultas[i].toString());
        }

        builder.append("/");
        builder.append(capaSalida.toString());

        return builder.toString();
    }
}
