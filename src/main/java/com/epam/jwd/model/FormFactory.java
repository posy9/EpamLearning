package com.epam.jwd.model;

import com.epam.jwd.context.FigureContext;
import com.epam.jwd.exception.IllegalFigureNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FormFactory implements FigureFactory {
    private static final Logger LOG = LogManager.getLogger(FormFactory.class);

    FormFactory() {

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
