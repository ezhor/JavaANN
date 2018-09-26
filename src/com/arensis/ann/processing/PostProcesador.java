package com.arensis.ann.processing;

public interface PostProcesador<S> {
    S procesar(double[] salidas);
    double calcularError(S salidaEsperada, double[] salidaObtenida);
}
