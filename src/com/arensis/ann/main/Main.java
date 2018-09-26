package com.arensis.ann.main;

public class Main {
    public static void main(String[] args){
        InteligenciaArtificial veronica = new Veronica();
        try {
            veronica.nacer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
