package com.epam.jwd.parser;

import com.epam.jwd.model.Component;
import com.epam.jwd.model.TextComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextToParagraphParser implements Parser {

    private final Parser nextHandler = ParagraphToSentenceParser.getInstance();
    private static final Pattern PATTERN_TO_FIND_PARAGRAPH = Pattern.compile("\\t([^\\t]*)(?=\\t|$)");
    private static TextToParagraphParser instance;

    private TextToParagraphParser() {}

    public static TextToParagraphParser getInstance() {
        if (instance == null) {
            instance = new TextToParagraphParser();
        }
        return instance;
    }

    @Override
    public Component handle(String text) {
        Matcher matcher = PATTERN_TO_FIND_PARAGRAPH.matcher(text);
        List<Component> textComponents = new ArrayList<>();
        while (matcher.find()) {
            String paragraph = matcher.group().trim();
            Component paragraphComponent = nextHandler.handle(paragraph);
            textComponents.add(paragraphComponent);

        }
        return new TextComponent(textComponents);
    }
}
