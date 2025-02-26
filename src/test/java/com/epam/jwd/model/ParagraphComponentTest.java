package com.epam.jwd.model;

import com.epam.jwd.parser.ParagraphToSentenceParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParagraphComponentTest {

    ParagraphToSentenceParser parser;
    Component paragraphComponent;

    @BeforeEach
    void setUp() {
        parser = ParagraphToSentenceParser.getInstance();
        String inputText = """
                \tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularized in the 5(1&2&(3|(4&(^56&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.""";
        paragraphComponent = parser.handle(inputText);
    }

    @Test
    public void getText_shouldReturnParagraphWithCalculatedBitExpressions() {
        String expectedResult = """
                It has survived - not only (five) centuries, but also the leap into 52 electronic typesetting, remaining 0 essentially 9 unchanged. It was popularized in the 1 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.""";
        String result = paragraphComponent.getText();
        assertEquals(expectedResult, result);
    }
}
