package com.epam.jwd.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymbolComponentTest {

    SymbolComponent symbolComponent;

    @BeforeEach
    void setUp() {
        String inputSymbol = ",";
        symbolComponent = new SymbolComponent(inputSymbol);
    }

    @Test
    public void getText_shouldReturnSameWord() {
        String expectedResult = ",";
        String result = symbolComponent.getText();
        assertEquals(expectedResult, result);
    }
}