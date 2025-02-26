package com.epam.jwd.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitExpressionComponentTest {

    BitExpressionComponent bitExpressionComponent;

    @BeforeEach
    void setUp() {
        String inputBitExpression = "3>>5";
        bitExpressionComponent = new BitExpressionComponent(inputBitExpression);
    }

    @Test
    public void getText_shouldReturnCalculatedBitExpressions() {
        String expectedResult = "0";
        String result = bitExpressionComponent.getText();
        assertEquals(expectedResult, result);
    }
}

