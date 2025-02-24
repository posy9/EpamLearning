package com.epam.jwd.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionCalculatorTest {

    ExpressionCalculator calculator;

    @BeforeEach
    public void setUp() {
         calculator = ExpressionCalculator.getInstance();
    }

   @Test
    public void calculate_shouldReturnCorrectAnswerForBitExpression() {
        String inputBitExpression = "(~5|1&2<<(2|5>>2&71))|1200";
        int expectedResult = -6;
        int result = calculator.calculate(inputBitExpression);
        assertEquals(expectedResult, result);
    }
}
