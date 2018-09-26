package com.arensis.ann.test;

import org.junit.jupiter.api.Test;
import com.arensis.ann.processing.PostProcesador;
import com.arensis.ann.processing.PostProcesadorColor;

import static org.junit.jupiter.api.Assertions.*;

class PostProcesadorColorTest {
    private PostProcesador<Integer> post = new PostProcesadorColor();

    @Test
    void procesarBlanco() {
        int salida =  post.procesar(new double[]{1d});
        assertEquals(-1, salida);
    }

    @Test
    void procesarNegro() {
        int salida =  post.procesar(new double[]{-1d});
        assertEquals(-16777216, salida);
    }

    @Test
    void calcularError() {
    }
}