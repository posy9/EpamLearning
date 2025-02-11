package com.epam.jwd.action;

import com.epam.jwd.creation.PlaneCreator;
import com.epam.jwd.exception.IllegalCoordinatePlaneException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.model.Plane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class PlaneCalculatorTest {


    PlaneCalculator planeAction = PlaneCalculator.getInstance();


    private static Stream<Arguments> coordinatePlaneAndDegreesProvider() {
        List<List<Dot>> listOfCoordinatePlanes = Arrays.asList(
                Arrays.asList(
                        new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("0")),
                        new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("0")),
                        new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("0"))
                ),
                Arrays.asList(
                        new Dot(new BigDecimal("3.0"), new BigDecimal("0"), new BigDecimal("3.0")),
                        new Dot(new BigDecimal("7.0"), new BigDecimal("0"), new BigDecimal("40.0")),
                        new Dot(new BigDecimal("10.0"), new BigDecimal("0"), new BigDecimal("10.0"))
                ),
                Arrays.asList(
                        new Dot(new BigDecimal("0"), new BigDecimal("11"), new BigDecimal("5.0")),
                        new Dot(new BigDecimal("0"), new BigDecimal("14"), new BigDecimal("10.0")),
                        new Dot(new BigDecimal("0"), new BigDecimal("13"), new BigDecimal("15.0"))
                ));
        PlaneCreator planeCreator = PlaneCreator.getInstance();
        List<Plane> coordinatePlanes = planeCreator.createPlanes(listOfCoordinatePlanes);
        return Stream.of(
                arguments(coordinatePlanes.getFirst(), new BigDecimal("36.09")),
                arguments(coordinatePlanes.get(1), new BigDecimal("59.66")),
                arguments(coordinatePlanes.get(2), new BigDecimal("72.36"))
        );
    }

    private static Stream<Arguments> InvalidCoordinatePlaneProvider() {
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
                arguments(coordinatePlanes.getFirst(), null),
                arguments(coordinatePlanes.get(1), null),
                arguments(coordinatePlanes.get(2), null)
        );
    }


    @ParameterizedTest
    @MethodSource("coordinatePlaneAndDegreesProvider")
    public void angleWithCoordinatePlane_shouldFindAngle_whenCoordinatePlaneIsValid(Plane param, BigDecimal expectedResult) {
        Plane mockPlane = new Plane(new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("9"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("7"), new BigDecimal("8"), new BigDecimal("9")));


        BigDecimal result = planeAction.angleWithCoordinatePlane(mockPlane, param);

        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("InvalidCoordinatePlaneProvider")
    public void angleWithCoordinatePlane_shouldThrowException_whenCoordinatePlaneIsNotValid(Plane param, BigDecimal expectedResult) {
        Plane mockPlane = new Plane(new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("9"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("7"), new BigDecimal("8"), new BigDecimal("9")));
        assertThrows(IllegalCoordinatePlaneException.class, () -> planeAction.angleWithCoordinatePlane(mockPlane, param));


    }


    @Test
    public void isNormal_shouldReturnTrue_whenPlaneNormal() {
        Plane mockPlane = new Plane(new Dot(new BigDecimal("1"), new BigDecimal("5"), new BigDecimal("3")),
                new Dot(new BigDecimal("9"), new BigDecimal("5"), new BigDecimal("6")),
                new Dot(new BigDecimal("7"), new BigDecimal("5"), new BigDecimal("9")));
        boolean result = planeAction.isNormal(mockPlane);
        assertTrue(result);
    }

    @Test
    public void isNormal_shouldReturnFalse_whenPlaneIsNotNormal() {
        Plane mockPlane = new Plane(new Dot(new BigDecimal("1"), new BigDecimal("3"), new BigDecimal("3")),
                new Dot(new BigDecimal("9"), new BigDecimal("5"), new BigDecimal("6")),
                new Dot(new BigDecimal("7"), new BigDecimal("5"), new BigDecimal("9")));
        boolean result = planeAction.isNormal(mockPlane);
        assertFalse(result);
    }


}
