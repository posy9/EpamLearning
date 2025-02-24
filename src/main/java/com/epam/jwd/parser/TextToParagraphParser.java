package com.epam.jwd.parser;

import com.epam.jwd.factory.ComponentFactory;
import com.epam.jwd.model.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextToParagraphParser implements Parser {

    private final Parser nextHandler = ParagraphToSentenceParser.getInstance();
    private static final Pattern PATTERN_TO_FIND_PARAGRAPH = Pattern.compile("\\t([^\\t]*)(?=\\t|$)"); ;
    private static final String CONTEXT_FOR_PARAGRAPH_COMPONENT_CREATION = "ParagraphComponent";
    private static TextToParagraphParser instance;
    private final ComponentFactory componentFactory = ComponentFactory.getInstance();

    private TextToParagraphParser() {}

    public static TextToParagraphParser getInstance() {
        if (instance == null) {
            instance = new TextToParagraphParser();
        }
        return instance;
    }

    @Override
    public void handle(Component component, String text) {
        Matcher matcher = PATTERN_TO_FIND_PARAGRAPH.matcher(text);
        while (matcher.find()) {
            String paragraph = matcher.group().trim() + "\n";
            Component paragraphComponent =  componentFactory.newInstanceOf(CONTEXT_FOR_PARAGRAPH_COMPONENT_CREATION);
            component.add(paragraphComponent);
            nextHandler.handle(paragraphComponent, paragraph);
        }
    }
}
