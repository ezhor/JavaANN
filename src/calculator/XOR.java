package calculator;

public class XOR {
    public boolean calcular(boolean a, boolean b){
        return ( (a||b) && !(a&&b) );
    }

    public double error(boolean a, boolean b, double salida){
        double error;
        if(calcular(a,b)){
            error = 1-salida;
        }else{
            error = salida;
        }
        if(error<0){
            error = error*(-1);
        }
        return error;
    }
}
