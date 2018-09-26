package com.arensis.ann.processing;

public class PostProcessadorXOR implements PostProcesador<Boolean>{
    @Override
    public Boolean procesar(double[] salidas) {
        boolean resultado = false;
        if(Math.round(salidas[0]) == 1){
            resultado = true;
        }
        return resultado;
    }

    @Override
    public double calcularError(Boolean salidaEsperada, double[] salidaObtenida) {
        double error;
        double salida = salidaObtenida[0];

        if(salidaEsperada){
            error = 1d - salida;
        }else{
            if(salida >0){
                error = salida;
            }else{
                error = salida*(-1);
            }
        }
        return error;
    }


}
