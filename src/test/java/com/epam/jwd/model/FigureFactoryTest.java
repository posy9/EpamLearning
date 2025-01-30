package com.epam.jwd.model;

import com.epam.jwd.context.FigureContext;
import com.epam.jwd.exception.IllegalFigureNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FigureFactoryTest {

    private FigureFactory formFactory;

    @BeforeEach
    void setUp() {
        formFactory = FigureFactory.newInstance();
    }

    @Test
    void createFigure_shouldCreatePlane_whenContextIsValid() throws IllegalFigureNameException {

        FigureContext context = FigureContext.of("Plane",new Dot(0,1,2),new Dot(3,7,9),
                new Dot(4,5,6));
        Figure plane = formFactory.createFigure(context);
        assertInstanceOf(Plane.class, plane);
    }

    @Test
    void createFigure_shouldThrowException_whenContextIsNotValid() throws IllegalFigureNameException {

        FigureContext context = FigureContext.of("A",new Dot(0,1,2),new Dot(3,7,9),
                new Dot(4,5,6));

        assertThrows(IllegalFigureNameException.class, () -> {
            formFactory.createFigure(context);
        });
    }

}
