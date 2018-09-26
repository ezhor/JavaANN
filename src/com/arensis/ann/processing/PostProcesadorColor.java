package com.arensis.ann.processing;

public class PostProcesadorColor implements PostProcesador<Integer>{
    @Override
    public Integer procesar(double[] salidas) {
        double salida = salidas[0];
        salida --;
        salida /= 2;
        salida *= 16777215;
        salida --;
        return (int)Math.round(salida);
    }

    @Override
    public double calcularError(Integer salidaEsperada, double[] salidaObtenida) {
        double error;
        int obtenida = procesar(salidaObtenida)*(-1);
        salidaEsperada = salidaEsperada*(-1);
        error = obtenida-salidaEsperada;
        if(error < 0){
            error *= -1;
        }
        error /= 16777216;
        return error;
    }
}
