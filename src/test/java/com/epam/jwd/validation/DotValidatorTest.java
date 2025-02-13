package com.epam.jwd.validation;

import com.epam.jwd.exception.IllegalDotCoordinatesException;
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

public class DotValidatorTest {

    private DotValidator dotValidator;

    @BeforeEach
    void setUp() {
        dotValidator = DotValidator.getInstance();
    }

    @ParameterizedTest
    @MethodSource("wrongCoordinatesProvider")
    public void isValidDot_shouldThrowException_whenDotIsInvalid(List<BigDecimal> wrongCoordinates) {
        assertThrows(IllegalDotCoordinatesException.class, () -> {
            dotValidator.isValidDot(wrongCoordinates);
        });
    }

    private static Stream<Arguments> wrongCoordinatesProvider() {
        return Stream.of(
                arguments(Arrays.asList(1.0, 2.0, 3.0)),
                arguments(List.of())
        );
    }
}
