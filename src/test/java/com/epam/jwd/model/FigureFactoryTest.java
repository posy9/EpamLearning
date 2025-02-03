package com.epam.jwd.model;

import com.epam.jwd.context.FigureContext;
import com.epam.jwd.exception.IllegalFigureNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class FigureFactoryTest {

    private FigureFactory formFactory;

    @BeforeEach
    void setUp() {
        formFactory = FigureFactory.newInstance();
    }

    @Test
    void createFigure_shouldCreatePlane_whenContextIsValid() throws IllegalFigureNameException {

        FigureContext context = FigureContext.of("Plane",new Dot(new BigDecimal("0"),new BigDecimal("1"),new BigDecimal("2")),
                new Dot(new BigDecimal("3"),new BigDecimal("7"),new BigDecimal("9")),
                new Dot(new BigDecimal("4"),new BigDecimal("5"),new BigDecimal("6")));
        Figure plane = formFactory.createFigure(context);
        assertInstanceOf(Plane.class, plane);
    }

    @Test
    void createFigure_shouldThrowException_whenContextIsNotValid() throws IllegalFigureNameException {

        FigureContext context = FigureContext.of("A",new Dot(new BigDecimal("0"),new BigDecimal("1"),new BigDecimal("2")),
                new Dot(new BigDecimal("3"),new BigDecimal("7"),new BigDecimal("9")),
                new Dot(new BigDecimal("4"),new BigDecimal("5"),new BigDecimal("6")));

        assertThrows(IllegalFigureNameException.class, () -> {
            formFactory.createFigure(context);
        });
    }

}
