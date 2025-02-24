package com.epam.jwd.action;

import com.epam.jwd.model.TextComponent;
import com.epam.jwd.parser.TextToParagraphParser;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentSorterTest {

    @Test
    public void paragraphsBySentencesSort_shouldReturnCorrectListOfParagraphsInCorrectOrder() {
        String inputText = """
                \tHello World. Two Sentences.
                \tOne Sentence.""";
        List<String> expectedResult = List.of("One Sentence. ", "Hello World. Two Sentences. ");
        TextComponent component = new TextComponent();
        TextToParagraphParser parser =TextToParagraphParser.getInstance();
        parser.handle(component,inputText);
        ComponentSorter sorter = ComponentSorter.getInstance();
        List<String> result = sorter.paragraphsBySentencesSort(component);
        System.out.println(result);
        assertEquals(expectedResult, result);
    }
}
