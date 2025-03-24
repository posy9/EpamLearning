package com.epam.jwd.model;

import java.util.List;
import java.util.stream.Collectors;

public class TextComponent implements Component {

    private static final String TABULATION_SEPARATOR = "\t";
    private static final String NEXT_LINE_SEPARATOR = "\n";

    private final List<Component> textComponents;

    public TextComponent(List<Component> textComponents) {
        this.textComponents = textComponents;
    }

    @Override
    public String getText() {
        return  textComponents.stream()
                .map(component -> TABULATION_SEPARATOR + component.getText())
                .collect(Collectors.joining(NEXT_LINE_SEPARATOR));
    }

    public List<Component> getComponents() {
        return textComponents;
    }
}
