package com.arensis.ann.trainning;

import com.arensis.ann.neural.ExcepcionNeuronal;

public interface IEntrenador {
    void entrenar(int iteraciones) throws ExcepcionNeuronal;
}
