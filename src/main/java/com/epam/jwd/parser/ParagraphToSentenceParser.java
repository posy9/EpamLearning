package com.epam.jwd.parser;

import com.epam.jwd.model.Component;
import com.epam.jwd.model.ParagraphComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphToSentenceParser implements Parser {

    private static final Parser nextHandler = SentenceToLexemeParser.getInstance();
    private static final Pattern PATTERN_TO_FIND_SENTENCE = Pattern.compile("[^.!?]*\\.\\.\\.|[^.!?]*[.!?]");
    private static ParagraphToSentenceParser instance;

    private ParagraphToSentenceParser() {}

    public static ParagraphToSentenceParser getInstance() {
        if (instance == null) {
            instance = new ParagraphToSentenceParser();
        }
        return instance;
    }

    @Override
    public Component handle(String paragraph) {
        Matcher matcher = PATTERN_TO_FIND_SENTENCE.matcher(paragraph);
        List<Component> paragraphComponents = new ArrayList<>();
        while (matcher.find()) {
            String sentence = matcher.group().trim();
            Component sentenceComponent = nextHandler.handle(sentence);
            paragraphComponents.add(sentenceComponent);

        }
        return new ParagraphComponent(paragraphComponents);
    }
}
