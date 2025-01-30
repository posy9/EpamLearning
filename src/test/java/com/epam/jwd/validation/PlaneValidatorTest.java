package com.epam.jwd.validation;


import com.epam.jwd.exception.IllegalDotCoordinatesException;
import com.epam.jwd.exception.IllegalPlaneCoordinatesException;
import com.epam.jwd.model.Dot;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PlaneValidatorTest {

    private PlaneValidator planeValidator;

    @BeforeEach
    void setUp() {
        planeValidator = new PlaneValidator();
    }

    @ParameterizedTest
    @MethodSource("wrongDotsProvider")
    public void isValidDot_shouldThrowException_whenDotIsInvalid(List<Dot> wrongDots)  {

        assertThrows(IllegalPlaneCoordinatesException.class, () -> {
            planeValidator.isValidPlane(wrongDots.getFirst(),wrongDots.get(1),wrongDots.get(2));
        });
    }

    private static Stream<Arguments> wrongDotsProvider() {
        return Stream.of(
                arguments(Arrays.asList(new Dot(1,2,3),new Dot(1,2,3),new Dot(1,2,3))),
                arguments(Arrays.asList(new Dot(1,2,3),new Dot(2,4,6),new Dot(3,6,9)))
        );
    }
}
