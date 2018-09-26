package com.arensis.ann.processing;

public class PostProcesadorDummy implements PostProcesador<double[]>{
    @Override
    public double[] procesar(double[] salidas) {
        return salidas;
    }

    @Override
    public double calcularError(double[] salidaObtenida, double[] salidaEsperada) {
        double error = 0;
        for (int j = 0; j < salidaEsperada.length; j++) {
            error = salidaEsperada[j] - salidaObtenida[j];
            if(error < 0){
                error *= -1;
            }
        }
        return error;
    }
}
