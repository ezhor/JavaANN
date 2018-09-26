package com.arensis.ann.neural;

public class NeuronaOculta extends Neurona {
    private Sinapsis[] sinapsis;
    private double bias;

    public NeuronaOculta(Capa capa, double bias){
        this.sinapsis = sinapsisAleatorias(capa);
        this.bias = bias;
    }

    public Sinapsis[] getSinapsis() {
        return sinapsis;
    }

    protected Sinapsis[] sinapsisAleatorias(Capa capa){
        Sinapsis[] sinapsis = new Sinapsis[capa.getNeuronas().length];
        Neurona[] neuronas = capa.getNeuronas();

        for(int i=0; i<sinapsis.length; i++){
            sinapsis[i] = new Sinapsis(neuronas[i], pesoAleatorio());
        }
        return sinapsis;
    }

    @Override
    public double propagar() {
        double resultado = 0;

        for(int i=0; i<sinapsis.length; i++){
            resultado += sinapsis[i].propagar();
        }

        //return this.sigmoide(resultado+bias);
        return Math.tanh(resultado+bias);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sinapsis.length; i++) {
            if(i>0){
                builder = builder.append(",");
            }
            builder.append(sinapsis[i].getPeso());
        }
        return builder.toString();
    }
}
