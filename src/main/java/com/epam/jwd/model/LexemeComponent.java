package com.epam.jwd.model;

import java.util.List;
import java.util.stream.Collectors;

public class LexemeComponent implements Component {

    private final List<Component> lexemeComponents;

    public LexemeComponent(List<Component> lexemeComponents) {
        this.lexemeComponents = lexemeComponents;
    }

    @Override
    public String getText() {
        return  lexemeComponents.stream()
                .map(Component::getText)
                .collect(Collectors.joining());
    }

    @Override
    public List<Component> getComponents() {
        return lexemeComponents;
    }
}
