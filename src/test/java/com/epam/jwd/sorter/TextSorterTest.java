package com.epam.jwd.sorter;

import com.epam.jwd.model.Component;
import com.epam.jwd.parser.TextToParagraphParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextSorterTest {

    TextSorter textSorter;
    TextToParagraphParser parser;
    Component textComponent;

    @BeforeEach
    void setUp() {
        textSorter = TextSorter.getInstance();
        parser = TextToParagraphParser.getInstance();
        String inputText = """
                \tHello, first paragraph with one Sentence.
                \tSecond paragraph with two sentences. Yes.
                """;
        textComponent = parser.handle(inputText);
    }

    @Test
    public void sort_ShouldReturnTextWithParagraphsSortedBySentencesByAscending_whenOrderVariationsIsAscendingAndSorterVariationsIsParagraphBySentences() {
        String expectedResult = """
            \tHello, first paragraph with one Sentence.
            \tSecond paragraph with two sentences. Yes.
            """;
        String result = textSorter.sort(SorterVariations.PARAGRAPHS_BY_SENTENCES,OrderVariations.ASCENDING,textComponent);
        assertTrue(equalsIgnoreLineSeparators(expectedResult, result));
    }

    @Test
    public void sort_ShouldReturnTextWithParagraphsSortedBySentencesByDescending_whenOrderVariationsIsDescendingAndSorterVariationsIsParagraphBySentences() {
        String expectedResult = """
            \tSecond paragraph with two sentences. Yes.
            \tHello, first paragraph with one Sentence.
            """;
        String result = textSorter.sort(SorterVariations.PARAGRAPHS_BY_SENTENCES,OrderVariations.DESCENDING,textComponent);
        assertTrue(equalsIgnoreLineSeparators(expectedResult, result));
    }

    @Test
    public void sort_ShouldReturnTextWithSentencesSortedByLexemesLength_whenOrderVariationsIsAscendingAndSorterVariationsIsSentencesByLexemes() {
        String expectedResult = """
            \tone with first Hello, paragraph Sentence.
            \ttwo with Second paragraph sentences. Yes.
            """;
        String result = textSorter.sort(SorterVariations.SENTENCES_BY_LEXEMES,OrderVariations.ASCENDING,textComponent);
        assertTrue(equalsIgnoreLineSeparators(expectedResult, result));
    }

    @Test
    public void sort_ShouldReturnTextWithSentencesSortedByLexemesLength_whenOrderVariationsIsDescendingAndSorterVariationsIsSentencesByLexemes() {
        String expectedResult = """
            \tparagraph Sentence. Hello, first with one
            \tsentences. paragraph Second with two Yes.
            """;
        String result = textSorter.sort(SorterVariations.SENTENCES_BY_LEXEMES,OrderVariations.DESCENDING,textComponent);
        assertTrue(equalsIgnoreLineSeparators(expectedResult, result));
    }

    private boolean equalsIgnoreLineSeparators(String text1, String text2) {
        String normalizedText1 = text1.replaceAll("\\r\\n|\\r|\\n", "");
        String normalizedText2 = text2.replaceAll("\\r\\n|\\r|\\n", "");
        return normalizedText1.equals(normalizedText2);
    }
}
