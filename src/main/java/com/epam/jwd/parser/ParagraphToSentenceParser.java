package com.epam.jwd.parser;

import com.epam.jwd.factory.ComponentFactory;
import com.epam.jwd.model.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphToSentenceParser implements Parser {

    private static final String CONTEXT_FOR_SENTENCE_COMPONENT_CREATION = "SentenceComponent";
    private static final Parser nextHandler = SentenceToLexemeParser.getInstance();
    private static final Pattern PATTERN_TO_FIND_SENTENCE = Pattern.compile("[^.!?]*\\.\\.\\.|[^.!?]*[.!?]");
    private static ParagraphToSentenceParser instance;
    private final ComponentFactory componentFactory = ComponentFactory.getInstance();

    private ParagraphToSentenceParser() {}

    public static ParagraphToSentenceParser getInstance() {
        if (instance == null) {
            instance = new ParagraphToSentenceParser();
        }
        return instance;
    }

    @Override
    public void handle(Component component, String paragraph) {
        Matcher matcher = PATTERN_TO_FIND_SENTENCE.matcher(paragraph);
        while (matcher.find()) {
            String sentence = matcher.group().trim();
            Component sentenceComponent =  componentFactory.newInstanceOf(CONTEXT_FOR_SENTENCE_COMPONENT_CREATION);
            component.add(sentenceComponent);
            nextHandler.handle(sentenceComponent, sentence);
        }
    }
}
