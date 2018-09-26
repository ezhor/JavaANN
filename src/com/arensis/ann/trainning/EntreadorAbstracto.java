package com.arensis.ann.trainning;

import com.arensis.ann.genetic.Poblacion;

public abstract class EntreadorAbstracto<S> implements IEntrenador {
    private EntrenadorConfig config;
    private Entrenamiento<S>[] entrenamientos;
    private Poblacion poblacion;

    public EntreadorAbstracto(EntrenadorConfig config, Entrenamiento<S>[] entrenamientos) {
        this.config = config;
        this.entrenamientos = entrenamientos;
        poblacion = new Poblacion<S>(config.getNumeroIndividuos(), config.getNumeroNeuronasEntrada(),
                config.getNumeroCapasOcultas(), config.getNumeroNeuronasPorCapaOculta(),
                config.getNumeroNeuronasSalida(), config.getBias(), config.getPostProcesador());
    }

    public EntrenadorConfig getConfig() {
        return config;
    }

    public void setConfig(EntrenadorConfig config) {
        this.config = config;
    }

    public Entrenamiento<S>[] getEntrenamientos() {
        return entrenamientos;
    }

    public void setEntrenamientos(Entrenamiento<S>[] entrenamientos) {
        this.entrenamientos = entrenamientos;
    }

    public Poblacion getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(Poblacion poblacion) {
        this.poblacion = poblacion;
    }
}
