package com.epam.jwd.parser;

import com.epam.jwd.model.Component;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParagraphToSentenceParserTest {

    @Test
    public void handle_shouldReturnParagraphComponentWithCorrectQuantityOfSentenceComponents() {
        String inputParagraph = "It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. Hui was popularized in the 5(1&2&(3|(4&(^56&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        ParagraphToSentenceParser parser = ParagraphToSentenceParser.getInstance();
        Component result = parser.handle(inputParagraph);
        assertEquals(2, result.getComponents().size());
    }
}
