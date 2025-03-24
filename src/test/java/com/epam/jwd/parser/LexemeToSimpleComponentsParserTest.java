package com.epam.jwd.parser;

import com.epam.jwd.model.Component;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexemeToSimpleComponentsParserTest {

    @Test
    public void handle_shouldReturnLexemeComponentWithCorrectQuantityOfSimpleComponents() {
        String inputLexeme = "it.";
        LexemeToSimpleComponentsParser parser = LexemeToSimpleComponentsParser.getInstance();
        Component result = parser.handle(inputLexeme);
        assertEquals(2, result.getComponents().size());
    }
}
