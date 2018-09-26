package com.arensis.ann.trainning;

import com.arensis.ann.genetic.Individuo;
import com.arensis.ann.neural.ExcepcionNeuronal;
import com.arensis.ann.neural.RedNeuronal;

import java.util.ArrayList;

public class EntrenadorGenetico<S> extends EntreadorAbstracto{

    public EntrenadorGenetico(EntrenadorConfig config, Entrenamiento<S>[] entrenamientos) {
        super(config, entrenamientos);
    }

    @Override
    public void entrenar(int iteraciones) throws ExcepcionNeuronal {
        Individuo[] individuos;
        for (int i = 0; i < iteraciones; i++) {
            getPoblacion().nuevaGeneracion();
            individuos = getPoblacion().getIndividuos();
            for (int j = 0; j < individuos.length; j++) {
                individuos[j].setAptitud(getAptitud(individuos[j]));
            }
        }
    }

    private double getAptitud(Individuo individuo) throws ExcepcionNeuronal {
        return 100-(getErrorMedio(individuo.getAlelo())*100);
    }

    private double getErrorMedio(RedNeuronal<S> redNeuronal) throws ExcepcionNeuronal {
        Entrenamiento<S>[] entrenamientos;
        ArrayList<Entrenamiento<S>> entrenamientosSeleccionados = new ArrayList<>();

        if(this.getConfig().getPorcentajeEntrenamientosAleatorios() == 100){
            entrenamientos = getEntrenamientos();
        }else{
            for (int i = 0; i < getEntrenamientos().length; i++) {
                if(Math.random()*100 <= getConfig().getPorcentajeEntrenamientosAleatorios()){
                    entrenamientosSeleccionados.add(getEntrenamientos()[i]);
                }
            }
            entrenamientos = entrenamientosSeleccionados.toArray(new Entrenamiento[entrenamientosSeleccionados.size()]);
        }
        return getErrorMedioParam(redNeuronal, entrenamientos);
    }

    private double getErrorMedioParam(RedNeuronal<S> redNeuronal, Entrenamiento<S>[] entrenamientos) throws ExcepcionNeuronal {
        double errorEntrenamiento;
        double errorTotal = 0;
        Entrenamiento<S> entrenamiento;
        S salida;
        double[] entradas;
        for (int i = 0; i < entrenamientos.length; i++) {
            entrenamiento = entrenamientos[i];
            salida = entrenamiento.getSalida();
            entradas = getEntrenamientos()[i].getEntradas();
            errorEntrenamiento = redNeuronal.calcularError(salida, entradas);

            errorTotal += errorEntrenamiento;
        }
        return errorTotal/getEntrenamientos().length;
    }
}
