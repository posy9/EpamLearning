package com.epam.jwd.action;

import com.epam.jwd.exception.IllegalCoordinatePlaneException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PlaneActionTest {




    @Mock
    private Plane mockPlane;

    @InjectMocks
    private PlaneAction planeAction;

    private static Stream<Arguments> coordinatePlaneAndDegreesProvider() {
        return Stream.of(
                arguments("xy", 36.1),
                arguments("yx",36.1),
                arguments("xz",59.7),
                arguments("zx",59.7),
                arguments("yz",72.4),
                arguments("zy",72.4)
        );
    }



    @ParameterizedTest
    @MethodSource("coordinatePlaneAndDegreesProvider")
    public void angleWithCoordinatePlane_shouldFindAngle_whenCoordinatePlaneIsValid(String param,double expectedResult) throws IllegalCoordinatePlaneException {
        Dot dotA = new Dot(1, 2, 3);
        Dot dotB = new Dot(9, 2, 6);
        Dot dotC = new Dot(7, 8, 9);
        lenient().when(mockPlane.getA()).thenReturn(dotA);
        lenient().when(mockPlane.getB()).thenReturn(dotB);
        lenient().when(mockPlane.getC()).thenReturn(dotC);

        double result = planeAction.angleWithCoordinatePlane(mockPlane, param);

        assertEquals(expectedResult, result, 0.1);
    }


    @Test
    public void angleWithCoordinatePlane_shouldThrowException_whenCoordinatePlaneIsInvalid() {
        assertThrows(IllegalCoordinatePlaneException.class, () -> {
            planeAction.angleWithCoordinatePlane(mockPlane, "invalid");
        });
    }

    @Test
    public void isNormal_shouldReturnTrue_whenPlaneNormal() {
        Dot dotA = new Dot(1, 5, 3);
        Dot dotB = new Dot(9, 5, 6);
        Dot dotC = new Dot(7, 5, 9);
        lenient().when(mockPlane.getA()).thenReturn(dotA);
        lenient().when(mockPlane.getB()).thenReturn(dotB);
        lenient().when(mockPlane.getC()).thenReturn(dotC);
        boolean result = planeAction.isNormal(mockPlane);
        assertTrue(result);
    }

    @Test
    public void isNormal_shouldReturnFalse_whenPlaneIsNotNormal() {
        Dot dotA = new Dot(1, 3, 3);
        Dot dotB = new Dot(9, 5, 6);
        Dot dotC = new Dot(7, 5, 9);
        lenient().when(mockPlane.getA()).thenReturn(dotA);
        lenient().when(mockPlane.getB()).thenReturn(dotB);
        lenient().when(mockPlane.getC()).thenReturn(dotC);
        boolean result = planeAction.isNormal(mockPlane);
        assertFalse(result);
    }




}
