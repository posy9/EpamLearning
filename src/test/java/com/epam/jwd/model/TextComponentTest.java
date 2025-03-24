package com.epam.jwd.model;

import com.epam.jwd.parser.TextToParagraphParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextComponentTest {

    TextToParagraphParser parser;
    Component textComponent;

    @BeforeEach
    void setUp() {
        parser = TextToParagraphParser.getInstance();
        String inputText = """
                \tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularized in the 5(1&2&(3|(4&(^56&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                \tIt is a long-established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.
                \tIt is a (~5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.
                \tBye.""";
        textComponent = parser.handle(inputText);
    }

    @Test
    public void getText_shouldReturnTextWithCalculatedBitExpressions() {
        String expectedResult = """
                \tIt has survived - not only (five) centuries, but also the leap into 52 electronic typesetting, remaining 0 essentially 9 unchanged. It was popularized in the 1 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                \tIt is a long-established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using 78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.
                \tIt is a -6 established fact that a reader will be of a page when looking at its layout.
                \tBye.""";
        String result = textComponent.getText();
        assertEquals(expectedResult, result);
    }

}
