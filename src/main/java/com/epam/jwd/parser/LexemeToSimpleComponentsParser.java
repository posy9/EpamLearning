package com.epam.jwd.parser;

import com.epam.jwd.factory.LeafComponentFactory;
import com.epam.jwd.model.Component;
import com.epam.jwd.model.LeafComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class LexemeToSimpleComponentsParser implements Parser {

    private static final String CONTEXT_FOR_WORD_COMPONENT_CREATION = "WordComponent";
    private static final String CONTEXT_FOR_BIT_EXPRESSION_COMPONENT_CREATION = "BitExpressionComponent";
    private static final String CONTEXT_FOR_SYMBOL_COMPONENT_CREATION = "SymbolComponent";
    private static LexemeToSimpleComponentsParser instance;
    private static final Pattern PATTERN_TO_FIND_SIMPLE_COMPONENTS =Pattern.compile("([\\d~^(]*((\\d+|[~^()<>&|]+|>>|<<)+)[\\d)])|(\\p{Alpha}+)|(\\p{Punct})");
    private final LeafComponentFactory leafComponentFactory = LeafComponentFactory.getInstance();

    private LexemeToSimpleComponentsParser() {}

    public static LexemeToSimpleComponentsParser getInstance() {
        if (instance == null) {
            instance = new LexemeToSimpleComponentsParser();
        }
        return instance;
    }

    @Override
    public void handle(Component component, String lexeme) {
        Matcher matcher = PATTERN_TO_FIND_SIMPLE_COMPONENTS.matcher(lexeme);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
              LeafComponent bitExpressionComponent = leafComponentFactory.newInstanceOf(CONTEXT_FOR_BIT_EXPRESSION_COMPONENT_CREATION);
              bitExpressionComponent.setContent(matcher.group(1));
              component.add(bitExpressionComponent);
            } else if (matcher.group(4) != null) {
                LeafComponent wordComponent = leafComponentFactory.newInstanceOf(CONTEXT_FOR_WORD_COMPONENT_CREATION);
                wordComponent.setContent(matcher.group(4));
                component.add(wordComponent);
            } else if (matcher.group(5) != null) {
                LeafComponent symbolComponent = leafComponentFactory.newInstanceOf(CONTEXT_FOR_SYMBOL_COMPONENT_CREATION);
                symbolComponent.setContent(matcher.group(5));
                component.add(symbolComponent);
            }
        }
    }
}
