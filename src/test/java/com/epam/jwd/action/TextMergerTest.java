package com.epam.jwd.action;

import com.epam.jwd.factory.ComponentFactory;
import com.epam.jwd.model.Component;
import com.epam.jwd.parser.TextToParagraphParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextMergerTest {

    @Test
    public void mergeText_shouldReturnCorrectText() {
        String inputText ="""
                \tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. Hui was popularized in the 5(1&2&(3|(4&(^56&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                \tIt is a long-established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.
                \tIt is a (~5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.
                \tBye.""";
        String expectedResult = """
                \tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. Hui was popularized in the 5(1&2&(3|(4&(^56&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\s
                \tIt is a long-established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\s
                \tIt is a (~5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\s
                \tBye.\s
                """;
        ComponentFactory factory = ComponentFactory.getInstance();
        Component inputTextComponent = factory.newInstanceOf("TextComponent");
        TextToParagraphParser parser = TextToParagraphParser.getInstance();
        parser.handle(inputTextComponent, inputText);
        TextMerger textMerger = TextMerger.getInstance();
        String result  = textMerger.mergeText(inputTextComponent);
        assertEquals(expectedResult, result);
    }
}
