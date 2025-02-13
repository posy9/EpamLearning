package com.epam.jwd.context;

import com.epam.jwd.model.Dot;

public interface FigureContext {
    String getFigureName();

    Dot getA();

    Dot getB();

    Dot getC();

    static FigureContext of(String figureName, Dot a, Dot b, Dot c) {
        return new FormContext(figureName, a, b, c);
    }
}
