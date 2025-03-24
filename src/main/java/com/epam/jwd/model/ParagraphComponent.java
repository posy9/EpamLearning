package com.epam.jwd.model;

import java.util.List;
import java.util.stream.Collectors;

public class ParagraphComponent implements Component {

    private static final String SPACE_SEPARATOR = " ";

    private final List<Component> paragraphComponents;

    public ParagraphComponent(List<Component> paragraphComponents) {
        this.paragraphComponents = paragraphComponents;
    }

    @Override
    public String getText() {
        return  paragraphComponents.stream()
                .map(Component::getText)
                .collect(Collectors.joining(SPACE_SEPARATOR));
    }

    public List<Component> getComponents() {
        return paragraphComponents;
    }
}
