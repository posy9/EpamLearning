package com.epam.jwd.validation;


import com.epam.jwd.creation.PlaneCreator;
import com.epam.jwd.exception.IllegalCoordinatePlaneException;
import com.epam.jwd.exception.IllegalPlaneCoordinatesException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PlaneValidatorTest {

    private final PlaneValidator planeValidator = PlaneValidator.getInstance();

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @MethodSource("wrongDotsProvider")
    public void isValidPlane_shouldThrowException_whenDotIsInvalid(List<Dot> wrongDots) {

        assertThrows(IllegalPlaneCoordinatesException.class, () -> {
            planeValidator.isValidPlane(wrongDots.getFirst(), wrongDots.get(1), wrongDots.get(2));
        });
    }

    @ParameterizedTest
    @MethodSource("wrongCoordinatePlanesProvider")
    public void isValidCoordinatePlane_shouldThrowException_whenPlaneIsNotCoordinatePlane(Plane inValidCoordinatePlane) {
        assertThrows(IllegalCoordinatePlaneException.class, () -> {
            planeValidator.isValidCoordinatePlane(inValidCoordinatePlane);
        });

    }

    private static Stream<Arguments> wrongDotsProvider() {
        return Stream.of(
                arguments(Arrays.asList(new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3")),
                        new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3")),
                        new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3")))),
                arguments(Arrays.asList(new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3")),
                        new Dot(new BigDecimal("2"), new BigDecimal("4"), new BigDecimal("6")),
                        new Dot(new BigDecimal("3"), new BigDecimal("6"), new BigDecimal("9"))))
        );
    }

    private static Stream<Arguments> wrongCoordinatePlanesProvider() {
        List<List<Dot>> listOfCoordinatePlanes = Arrays.asList(
                Arrays.asList(
                        new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("1")),
                        new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("0")),
                        new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("0"))
                ),
                Arrays.asList(
                        new Dot(new BigDecimal("3.0"), new BigDecimal("0"), new BigDecimal("5.0")),
                        new Dot(new BigDecimal("6.0"), new BigDecimal("2"), new BigDecimal("10.0")),
                        new Dot(new BigDecimal("9.0"), new BigDecimal("0"), new BigDecimal("15.0"))
                ),
                Arrays.asList(
                        new Dot(new BigDecimal("3"), new BigDecimal("11"), new BigDecimal("5.0")),
                        new Dot(new BigDecimal("0"), new BigDecimal("14"), new BigDecimal("10.0")),
                        new Dot(new BigDecimal("0"), new BigDecimal("13"), new BigDecimal("15.0"))
                )
        );
        PlaneCreator planeCreator = PlaneCreator.getInstance();
        List<Plane> coordinatePlanes = planeCreator.createPlanes(listOfCoordinatePlanes);
        return Stream.of(
                arguments(coordinatePlanes.getFirst()),
                arguments(coordinatePlanes.get(1)),
                arguments(coordinatePlanes.get(2))
        );
    }
}
