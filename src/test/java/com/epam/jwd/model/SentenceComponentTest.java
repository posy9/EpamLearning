package com.epam.jwd.model;

import com.epam.jwd.parser.SentenceToLexemeParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SentenceComponentTest {

    SentenceToLexemeParser parser;
    Component sentenceComponent;

    @BeforeEach
    void setUp() {
        parser = SentenceToLexemeParser.getInstance();
        String inputSentence = """
                It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged.""";
        sentenceComponent = parser.handle(inputSentence);
    }

    @Test
    public void getText_shouldReturnSentenceWithCalculatedBitExpressions() {
        String expectedResult = """
                It has survived - not only (five) centuries, but also the leap into 52 electronic typesetting, remaining 0 essentially 9 unchanged.""";
        String result = sentenceComponent.getText();
        assertEquals(expectedResult, result);
    }
}
