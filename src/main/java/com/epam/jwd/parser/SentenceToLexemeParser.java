package com.epam.jwd.parser;

import com.epam.jwd.factory.ComponentFactory;
import com.epam.jwd.model.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceToLexemeParser implements Parser {

    private static SentenceToLexemeParser instance;
    private final Parser nextHandler = LexemeToSimpleComponentsParser.getInstance();
    private final ComponentFactory componentFactory = ComponentFactory.getInstance();
    private static final Pattern PATTERN_TO_FIND_LEXEME = Pattern.compile("(?<=^|\\s)\\S+(?=\\s|$)"); ;
    private static final String CONTEXT_FOR_LEXEME_COMPONENT_CREATION = "LexemeComponent";

    private SentenceToLexemeParser() {}

    public static SentenceToLexemeParser getInstance() {
        if (instance == null) {
            instance = new SentenceToLexemeParser();
        }
        return instance;
    }

    @Override
    public void handle(Component component, String sentence) {
        Matcher matcher = PATTERN_TO_FIND_LEXEME.matcher(sentence);
        while (matcher.find()) {
            String lexeme = matcher.group().trim();
            Component lexemeComponent = componentFactory.newInstanceOf(CONTEXT_FOR_LEXEME_COMPONENT_CREATION);
            component.add(lexemeComponent);
            nextHandler.handle(lexemeComponent, lexeme);
        }

    }
}
