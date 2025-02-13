package com.epam.jwd.model;

import com.epam.jwd.context.FigureContext;
import com.epam.jwd.exception.IllegalFigureNameException;

public class FormFactory implements FigureFactory {
    private static FormFactory instance;

    private FormFactory() {}

    public static FormFactory getInstance() {
        if (instance == null) {
            instance = new FormFactory();
        }
        return instance;
    }

    @Override
    public Figure createFigure(FigureContext context) throws IllegalFigureNameException {
        Figure figure = null;
        if (context.getFigureName().equals("Plane")) {
            figure = new Plane(context.getA(), context.getB(), context.getC());
        } else {
            throw new IllegalFigureNameException("Invalid figure name");
        }
        return figure;
    }
}
