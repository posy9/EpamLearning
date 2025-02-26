package com.epam.jwd.parser;

import com.epam.jwd.model.Component;
import com.epam.jwd.model.SentenceComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceToLexemeParser implements Parser {

    private static SentenceToLexemeParser instance;
    private final Parser nextHandler = LexemeToSimpleComponentsParser.getInstance();
    private static final Pattern PATTERN_TO_FIND_LEXEME = Pattern.compile("(?<=^|\\s)\\S+(?=\\s|$)"); ;

    private SentenceToLexemeParser() {}

    public static SentenceToLexemeParser getInstance() {
        if (instance == null) {
            instance = new SentenceToLexemeParser();
        }
        return instance;
    }

    @Override
    public Component handle(String paragraph) {
        Matcher matcher = PATTERN_TO_FIND_LEXEME.matcher(paragraph);
        List<Component> sentenceComponents = new ArrayList<>();
        while (matcher.find()) {
            String sentence = matcher.group().trim();
            Component lexemeComponent = nextHandler.handle(sentence);
            sentenceComponents.add(lexemeComponent);
        }
        return new SentenceComponent(sentenceComponents);
    }
}
