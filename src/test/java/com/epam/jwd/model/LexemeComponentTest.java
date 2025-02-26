package com.epam.jwd.model;

import com.epam.jwd.parser.LexemeToSimpleComponentsParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexemeComponentTest {

    LexemeToSimpleComponentsParser parser;
    Component lexemeComponent;

    @BeforeEach
    void setUp() {
        parser = LexemeToSimpleComponentsParser.getInstance();
        String inputLexeme = "3>>5.";
        lexemeComponent = parser.handle(inputLexeme);
    }

    @Test
    public void getText_shouldReturnLexemeWithCalculatedBitExpressions() {
        String expectedResult = "0.";
        String result = lexemeComponent.getText();
        assertEquals(expectedResult, result);
    }
}

