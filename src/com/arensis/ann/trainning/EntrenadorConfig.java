package com.arensis.ann.trainning;

import com.arensis.ann.processing.PostProcesador;
import com.arensis.ann.processing.PostProcesadorDummy;

public class EntrenadorConfig {
    private int numeroIndividuos;
    private int numeroNeuronasEntrada;
    private int numeroCapasOcultas;
    private int numeroNeuronasPorCapaOculta;
    private int numeroNeuronasSalida;
    private double bias;
    private boolean cache;
    private PostProcesador postProcesador;
    private double porcentajeEntrenamientosAleatorios;

    public EntrenadorConfig() {
        this.numeroIndividuos = 1;
        this.numeroNeuronasEntrada = 1;
        this.numeroCapasOcultas = 1;
        this.numeroNeuronasPorCapaOculta = 1;
        this.numeroNeuronasSalida = 1;
        this.postProcesador = new PostProcesadorDummy();
        this.porcentajeEntrenamientosAleatorios = 100;
    }

    public int getNumeroIndividuos() {
        return numeroIndividuos;
    }

    public void setNumeroIndividuos(int numeroIndividuos) {
        this.numeroIndividuos = numeroIndividuos;
    }

    public int getNumeroNeuronasEntrada() {
        return numeroNeuronasEntrada;
    }

    public void setNumeroNeuronasEntrada(int numeroNeuronasEntrada) {
        this.numeroNeuronasEntrada = numeroNeuronasEntrada;
    }

    public int getNumeroCapasOcultas() {
        return numeroCapasOcultas;
    }

    public void setNumeroCapasOcultas(int numeroCapasOcultas) {
        this.numeroCapasOcultas = numeroCapasOcultas;
    }

    public int getNumeroNeuronasPorCapaOculta() {
        return numeroNeuronasPorCapaOculta;
    }

    public void setNumeroNeuronasPorCapaOculta(int numeroNeuronasPorCapaOculta) {
        this.numeroNeuronasPorCapaOculta = numeroNeuronasPorCapaOculta;
    }

    public int getNumeroNeuronasSalida() {
        return numeroNeuronasSalida;
    }

    public void setNumeroNeuronasSalida(int numeroNeuronasSalida) {
        this.numeroNeuronasSalida = numeroNeuronasSalida;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public PostProcesador getPostProcesador() {
        return postProcesador;
    }

    public void setPostProcesador(PostProcesador postProcesador) {
        this.postProcesador = postProcesador;
    }

    public double getPorcentajeEntrenamientosAleatorios() {
        return porcentajeEntrenamientosAleatorios;
    }

    public void setPorcentajeEntrenamientosAleatorios(double porcentajeEntrenamientosAleatorios) {
        this.porcentajeEntrenamientosAleatorios = porcentajeEntrenamientosAleatorios;
    }
}
