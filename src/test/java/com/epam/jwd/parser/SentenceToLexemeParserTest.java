package com.epam.jwd.parser;

import com.epam.jwd.model.Component;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SentenceToLexemeParserTest {

    @Test
    public void handle_shouldReturnParagraphComponentWithCorrectQuantityOfSentenceComponents() {
        String inputSentence= "It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged.";
        SentenceToLexemeParser parser = SentenceToLexemeParser.getInstance();
        Component result = parser.handle(inputSentence);
        assertEquals(21, result.getComponents().size());
    }
}
