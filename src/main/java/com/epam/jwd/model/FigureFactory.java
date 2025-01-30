package com.epam.jwd.model;

import com.epam.jwd.context.FigureContext;
import com.epam.jwd.exception.IllegalFigureNameException;

public interface FigureFactory {

    Figure createFigure(FigureContext context) throws IllegalFigureNameException;

    static FigureFactory newInstance() {
        return new FormFactory();
    }
}
