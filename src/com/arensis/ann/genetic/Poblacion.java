package com.arensis.ann.genetic;

import com.arensis.ann.processing.PostProcesador;

public class Poblacion<S>{
    private Individuo[] individuos;
    private Individuo madre;
    private Individuo padre;
    private int generacion = 1;

    public Poblacion(int numeroIndividuos, int numeroNeuronasEntrada, int numeroCapasOcultas, int numeroNeuronasPorCapaOculta, int numeroNeuronasSalida, double bias, PostProcesador postProcesador){
        individuos = new Individuo[numeroIndividuos];
        for (int i = 0; i < numeroIndividuos; i++) {
            individuos[i] = new Individuo<S>(numeroNeuronasEntrada, numeroCapasOcultas, numeroNeuronasPorCapaOculta, numeroNeuronasSalida, bias, postProcesador);
        }
    }

    public Poblacion(Individuo[] individuos){
        this.individuos = individuos;
    }

    public Individuo[] getIndividuos() {
        return individuos;
    }

    public int getGeneracion() {
        return generacion;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < individuos.length; i++) {
            if(i>0){
                builder.append("\n");
            }
            builder.append(individuos[i].getADN());
        }
        return builder.toString();
    }

    public void nuevaGeneracion(){
        this.madre = individuos[0];
        this.padre = individuos[1];
        Individuo actual;

        if(madre.getAptitud() < padre.getAptitud()){
            intercambiar(madre, padre);
        }

        for (int i = 2; i < individuos.length; i++) {
            actual = individuos[i];
            if(actual.getAptitud() > madre.getAptitud()){
                padre = actual;
                intercambiar(madre, padre);
            }else if(actual.getAptitud() > padre.getAptitud()){
                padre = actual;
            }
        }
        //System.out.println();
        //System.out.println("Madre seleccionada con aptitud: "+Math.round(madre.getAptitud())+"%");
        //System.out.println("Padre seleccionado con aptitud: "+Math.round(padre.getAptitud())+"%");
        //System.out.println("Madre seleccionada con aptitud: "+madre.getAptitud());
        //System.out.println("Padre seleccionado con aptitud: "+padre.getAptitud());

        this.individuos[0] = this.madre;
        this.individuos[1] = this.padre;

        for (int i = 2; i < individuos.length; i++) {
            individuos[i] = new Individuo(this.madre, this.padre);
        }
        this.generacion++;
    }

    private void intercambiar(Individuo madre, Individuo padre){
        Individuo auxiliar;
        auxiliar = madre;
        this.madre = padre;
        this.padre = auxiliar;
    }

    public Individuo mejorIndividuo(){
        Individuo individuo = individuos[0];
        for (int i = 1; i < individuos.length; i++) {
            if(individuos[i].getAptitud() > individuo.getAptitud()){
                individuo = individuos[i];
            }
        }
        return individuo;
    }
}
