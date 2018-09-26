package com.arensis.ann.genetic;

import com.arensis.ann.neural.*;

import java.io.*;

public class Noe {
    public void guardarPoblacion(Poblacion poblacion, String ruta) throws IOException {
        FileOutputStream fos = new FileOutputStream(ruta);
        fos.write(poblacion.toString().getBytes());
    }

    public void guardarAlelo(RedNeuronal alelo, String ruta) throws IOException {
        FileOutputStream fos = new FileOutputStream(ruta);
        fos.write(alelo.toString().getBytes());
    }

    /*public Poblacion cargarPoblacion(String ruta) throws IOException {
        Poblacion poblacion;
        ArrayList<Individuo> individuosList = new ArrayList<>();
        Individuo[] individuosArray;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
        String linea = reader.readLine();
        while(linea != null){
            individuosList.add(recuperarIndividuo(linea));
            linea = reader.readLine();
        }
        reader.close();
        poblacion = new Poblacion(individuosList.toArray(new Individuo[0]));

        return  poblacion;
    }

    private Individuo recuperarIndividuo(String alelo){
        return new Individuo(generarRedNeuronal(alelo));
    }

    private RedNeuronal generarRedNeuronal(String alelo){
        String[] capas = alelo.split("/");
        int numeroNeuronasEntrada = Integer.parseInt(capas[0]);
        String[] capasOcultasString = new String[capas.length-2];
        String capaVisibleString = capas[capas.length-1];
        System.arraycopy(capas, 1, capasOcultasString, 0, capas.length - 2);

        return new RedNeuronal(generarCapaEntrada(numeroNeuronasEntrada), generarCapasOcultas(capasOcultasString), generarCapaVisible(capaVisibleString));
    }

    private CapaEntrada generarCapaEntrada(int numeroNeuronas){
        return new CapaEntrada(numeroNeuronas);
    }

    private CapaOculta[] generarCapasOcultas(String[] capasOcultasString){
        CapaOculta[] capasOcultas = new CapaOculta[capasOcultasString.length];
        for (int i = 0; i < capasOcultasString.length; i++) {
            capasOcultas[i] = generarCapaOculta(capasOcultasString[i]);
        }
        return capasOcultas;
    }

    private CapaOculta generarCapaOculta(String capaOcultaString){
        NeuronaOculta[] neuronas;
        String[] neuronasString = capaOcultaString.split(";");
        for (int i = 0; i < neuronasString.length; i++) {
            neuronas = generarNeuronaOculta(neuronasString[i]);
        }
        return new CapaOculta(neuronas);
    }

    private CapaSalida generarCapaVisible(String capaVisibleString){

    }

    private NeuronaOculta[] generarNeuronaOculta(String s) {

    }*/
}
