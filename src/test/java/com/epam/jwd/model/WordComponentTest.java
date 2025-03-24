package com.epam.jwd.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordComponentTest {

    WordComponent wordComponent;

    @BeforeEach
    void setUp() {
        String inputWord = "Hello";
        wordComponent = new WordComponent(inputWord);
    }

    @Test
    public void getText_shouldReturnSameWord() {
        String expectedResult = "Hello";
        String result = wordComponent.getText();
        assertEquals(expectedResult, result);
    }
}