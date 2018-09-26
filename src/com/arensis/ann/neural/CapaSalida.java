package com.arensis.ann.neural;

import com.arensis.ann.processing.PostProcesador;

import java.io.Serializable;

public class CapaSalida<S> extends Capa<NeuronaSalida> implements Serializable {
    private PostProcesador<S> postProcesador;

    public CapaSalida(int numeroNeuronas, Capa capaPrevia, double bias, PostProcesador postProcesador){
        this.setNeuronas(generarNeuronas(numeroNeuronas, capaPrevia, bias));
        this.postProcesador = postProcesador;
    }

    @Override
    public NeuronaSalida[] getNeuronas() {
        return super.getNeuronas();
    }

    public PostProcesador<S> getPostProcesador() {
        return postProcesador;
    }

    public void setPostProcesador(PostProcesador<S> postProcesador) {
        this.postProcesador = postProcesador;
    }

    protected NeuronaSalida[] generarNeuronas(int cantidad, Capa capaPrevia, double bias) {
        NeuronaSalida[] neuronas = new NeuronaSalida[cantidad];
        for(int i=0; i<cantidad; i++){
            neuronas[i] = new NeuronaSalida(capaPrevia, bias);
        }
        return neuronas;
    }

    public double[] getSalidaSinProcesar(){
        double[] resultados = new double[this.getNeuronas().length];
        for (int i=0; i<resultados.length; i++) {
            resultados[i] = this.getNeuronas()[i].propagar();
        }
        return resultados;
    }

    public S getSalidaProcesada(){
        return postProcesador.procesar(getSalidaSinProcesar());
    }

    public double calcularError(S salidaEsperada, double[] salidaObtenida){
        return postProcesador.calcularError(salidaEsperada, salidaObtenida);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getNeuronas().length; i++) {
            if(i>0){
                builder = builder.append(";");
            }
            builder.append(getNeuronas()[i].toString());
        }
        return builder.toString();
    }
}
