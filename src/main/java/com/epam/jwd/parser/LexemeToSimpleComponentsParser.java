package com.epam.jwd.parser;

import com.epam.jwd.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeToSimpleComponentsParser implements Parser {

    private static final String CONTEXT_FOR_WORD_COMPONENT_CREATION = "WordComponent";
    private static final String CONTEXT_FOR_BIT_EXPRESSION_COMPONENT_CREATION = "BitExpressionComponent";
    private static final String CONTEXT_FOR_SYMBOL_COMPONENT_CREATION = "SymbolComponent";
    private static LexemeToSimpleComponentsParser instance;
    private static final Pattern PATTERN_TO_FIND_SIMPLE_COMPONENTS =Pattern.compile("([\\d~^(]*((\\d+|[~^()<>&|]+|>>|<<)+)[\\d)])|(\\p{Alpha}+)|(\\p{Punct})");


    private LexemeToSimpleComponentsParser() {}

    public static LexemeToSimpleComponentsParser getInstance() {
        if (instance == null) {
            instance = new LexemeToSimpleComponentsParser();
        }
        return instance;
    }

    @Override
    public Component handle(String lexeme) {
        Matcher matcher = PATTERN_TO_FIND_SIMPLE_COMPONENTS.matcher(lexeme);
        List<Component> lexemeComponents = new ArrayList<>();
        while (matcher.find()) {
            if (matcher.group(1) != null) {
              lexemeComponents.add(new BitExpressionComponent(matcher.group(1).trim()));
            } else if (matcher.group(4) != null) {
                lexemeComponents.add(new WordComponent(matcher.group(4).trim()));
            } else if (matcher.group(5) != null) {
                lexemeComponents.add(new SymbolComponent(matcher.group(5).trim()));
            }
        }
        return new LexemeComponent(lexemeComponents);
    }
}
