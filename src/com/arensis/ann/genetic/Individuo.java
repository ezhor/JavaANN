package com.arensis.ann.genetic;

import com.arensis.ann.neural.*;
import com.arensis.ann.processing.PostProcesador;

import java.io.Serializable;

public class Individuo<S> implements Serializable {
    private static double PROBABILIDAD_MUTACION = 0.3d;
    private static double INTENSIDAD_MUTACION = 0.1d;

    private RedNeuronal<S> alelo;
    private double aptitud;

    public Individuo(int numeroNeuronasEntrada, int numeroCapasOcultas, int numeroNeuronasPorCapaOculta, int numeroNeuronasSalida, double bias, PostProcesador postProcesador) {
        this.alelo = new RedNeuronal<>(numeroNeuronasEntrada, numeroCapasOcultas, numeroNeuronasPorCapaOculta, numeroNeuronasSalida, bias, postProcesador);
    }

    public Individuo(RedNeuronal alelo){
        this.alelo = alelo;
    }

    public Individuo(Individuo madre, Individuo padre){
        this.alelo = new RedNeuronal<S>(madre.getAlelo().getCapaEntrada().getNeuronas().length,
                madre.getAlelo().getCapasOcultas().length,
                madre.getAlelo().getCapasOcultas()[0].getNeuronas().length,
                madre.getAlelo().getCapaSalida().getNeuronas().length,
                madre.getAlelo().getBias(),
                madre.getAlelo().getCapaSalida().getPostProcesador());
        combinarGenes(madre, padre);
    }

    public RedNeuronal<S> getAlelo() {
        return alelo;
    }

    public double getAptitud() {
        return aptitud;
    }

    public void setAptitud(double aptitud) {
        this.aptitud = aptitud;
    }



    public String getADN(){
        return alelo.toString();
    }

    private boolean genDeMadre(){
        return Math.random() > 0.5d;
    }

    private void combinarGenes(Individuo madre, Individuo padre){
        CapaOculta capaOculta;
        CapaSalida capaSalida;
        NeuronaOculta neuronaOculta;
        NeuronaSalida neuronaSalida;
        Sinapsis sinapsis;

        for (int i = 0; i < this.alelo.getCapasOcultas().length; i++) {
            capaOculta = this.alelo.getCapasOcultas()[i];
            for (int j = 0; j < capaOculta.getNeuronas().length; j++) {
                neuronaOculta = capaOculta.getNeuronas()[j];
                for (int k = 0; k < neuronaOculta.getSinapsis().length; k++) {
                    sinapsis = neuronaOculta.getSinapsis()[k];
                    if(genDeMadre()){
                        sinapsis.setPeso(madre.getAlelo().getCapasOcultas()[i].getNeuronas()[j].getSinapsis()[k].getPeso());
                    }else{
                        sinapsis.setPeso(padre.getAlelo().getCapasOcultas()[i].getNeuronas()[j].getSinapsis()[k].getPeso());
                    }
                    mutar(sinapsis);
                }
            }
        }
        capaSalida = this.alelo.getCapaSalida();
        for (int i = 0; i < capaSalida.getNeuronas().length; i++) {
            neuronaSalida = (NeuronaSalida) capaSalida.getNeuronas()[i];
            for (int j = 0; j < neuronaSalida.getSinapsis().length; j++) {
                sinapsis = neuronaSalida.getSinapsis()[j];
                if(genDeMadre()){
                    sinapsis.setPeso(madre.getAlelo().getCapaSalida().getNeuronas()[i].getSinapsis()[j].getPeso());
                }else{
                    sinapsis.setPeso(padre.getAlelo().getCapaSalida().getNeuronas()[i].getSinapsis()[j].getPeso());
                }
                mutar(sinapsis);
            }
        }
    }

    private void mutar(Sinapsis sinapsis){
        if(muta()){
            sinapsis.setPeso(sinapsis.getPeso()+mutacionAleatoria());
        }
    }

    private boolean muta(){
        return Math.random() < PROBABILIDAD_MUTACION;
    }
    private double mutacionAleatoria(){
        return (Math.random()*INTENSIDAD_MUTACION*2)-INTENSIDAD_MUTACION;
    }
}