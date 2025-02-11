package com.epam.jwd.exception;

import com.epam.jwd.model.Figure;

public class FigureNotFoundException extends RuntimeException {

    public FigureNotFoundException(String message) {
        super(message);
    }
}
