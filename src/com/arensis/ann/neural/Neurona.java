package com.arensis.ann.neural;

public abstract class Neurona implements Propagacion {

    protected double sigmoide(double x){
        return 1 / (1 + Math.exp(-x));
    }

    public Neurona() {
    }

    public static double pesoAleatorio(){
        return ((Math.random()*2)-1);
    }


}
