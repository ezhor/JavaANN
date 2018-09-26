package com.arensis.ann.main;

import calculator.XOR;
import com.arensis.ann.genetic.Individuo;
import com.arensis.ann.neural.ExcepcionNeuronal;
import com.arensis.ann.processing.PostProcessadorXOR;
import com.arensis.ann.trainning.EntrenadorConfig;
import com.arensis.ann.trainning.EntrenadorGenetico;
import com.arensis.ann.trainning.Entrenamiento;

import java.io.IOException;

public class MainSoraya {
    public static void main(String[] args) throws ExcepcionNeuronal, IOException {
        EntrenadorGenetico entrenador = getNuevoEntrenador();
        XOR xor = new XOR();
        Individuo soraya;
        int generacion = 1;
        do{
            entrenador.entrenar(1);
            soraya = entrenador.getPoblacion().mejorIndividuo();
            System.out.println("Generación "+generacion);
            System.out.println("(0,0): " + soraya.getAlelo().calcularSalidaProcesada(0,0) +"("+ soraya.getAlelo().calcularSalida(0,0)[0]+", error: "+soraya.getAlelo().calcularError(false, 0,0)+")");
            System.out.println("(0,1): " + soraya.getAlelo().calcularSalidaProcesada(0,1) +"("+ soraya.getAlelo().calcularSalida(0,1)[0]+", error: "+soraya.getAlelo().calcularError(true, 0,1)+")");
            System.out.println("(1,0): " + soraya.getAlelo().calcularSalidaProcesada(1,0) +"("+ soraya.getAlelo().calcularSalida(1,0)[0]+", error: "+soraya.getAlelo().calcularError(true, 1,0)+")");
            System.out.println("(1,1): " + soraya.getAlelo().calcularSalidaProcesada(1,1) +"("+ soraya.getAlelo().calcularSalida(1,1)[0]+", error: "+soraya.getAlelo().calcularError(false, 1,1)+")");
            System.out.println("Aptitud: "+soraya.getAptitud()+"%\n");
            generacion++;
        }while (soraya.getAptitud() < 99);
        System.out.println("\nADN: "+ soraya.getADN());

        System.out.println("\n(0,0): " + soraya.getAlelo().calcularSalidaProcesada(0,0) +"("+ soraya.getAlelo().calcularSalida(0,0)[0]+")");
        System.out.println("\n(0,1): " + soraya.getAlelo().calcularSalidaProcesada(0,1) +"("+ soraya.getAlelo().calcularSalida(0,1)[0]+")");
        System.out.println("\n(1,0): " + soraya.getAlelo().calcularSalidaProcesada(1,0) +"("+ soraya.getAlelo().calcularSalida(1,0)[0]+")");
        System.out.println("\n(1,1): " + soraya.getAlelo().calcularSalidaProcesada(1,1) +"("+ soraya.getAlelo().calcularSalida(1,1)[0]+")");
    }

    @SuppressWarnings("Duplicates")
    private static EntrenadorGenetico getNuevoEntrenador() throws IOException {
        EntrenadorConfig config = new EntrenadorConfig();
        config.setNumeroNeuronasEntrada(2);
        config.setNumeroCapasOcultas(1);
        config.setNumeroNeuronasPorCapaOculta(3);
        config.setNumeroNeuronasSalida(1);
        config.setBias(1);
        config.setCache(false);
        config.setNumeroIndividuos(10);
        config.setPostProcesador(new PostProcessadorXOR());
        return new EntrenadorGenetico<Boolean>(config, getEntrenamientosXOR());
    }

    private static Entrenamiento[] getEntrenamientosXOR() {
        double[] entrada00 = {0,0};
        double[] entrada01 = {0,1};
        double[] entrada10 = {1,0};
        double[] entrada11 = {1,1};

        Entrenamiento<Boolean> entrenamiento1 = new Entrenamiento<>(entrada00, false);
        Entrenamiento<Boolean> entrenamiento2 = new Entrenamiento<>(entrada01, true);
        Entrenamiento<Boolean> entrenamiento3 = new Entrenamiento<>(entrada10, true);
        Entrenamiento<Boolean> entrenamiento4 = new Entrenamiento<>(entrada11, false);

        return new Entrenamiento[]{entrenamiento1, entrenamiento2, entrenamiento3, entrenamiento4};
    }
}
