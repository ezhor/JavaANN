package com.arensis.ann.processing;

public class PostProcesadorColorEstricto extends PostProcesadorColor{

    @Override
    public double calcularError(Integer salidaEsperada, double[] salidaObtenida) {
        double error;
        int obtenida = procesar(salidaObtenida)*(-1);
        salidaEsperada = salidaEsperada*(-1);
        error = salidaEsperada.equals(obtenida) ? 0d : 1d;
        return error;
    }
}
