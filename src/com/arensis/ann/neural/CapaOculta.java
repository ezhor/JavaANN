package com.arensis.ann.neural;

import java.io.Serializable;

public class CapaOculta extends Capa<NeuronaOculta> implements Serializable {

    public CapaOculta(int numeroNeuronas, Capa capaPrevia, double bias){
        this.setNeuronas(generarNeuronas(numeroNeuronas, capaPrevia, bias));
    }

    protected NeuronaOculta[] generarNeuronas(int cantidad, Capa capaPrevia, double bias) {
        NeuronaOculta[] neuronas = new NeuronaOculta[cantidad];
        for(int i=0; i<cantidad; i++){
            neuronas[i] = new NeuronaOculta(capaPrevia, bias);
        }
        return neuronas;
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
