package com.epam.jwd.model;

import java.util.List;
import java.util.stream.Collectors;

public class SentenceComponent implements Component {

    private static final String SPACE_SEPARATOR = " ";

    private final List<Component> sentenceComponents;

    public SentenceComponent(List<Component> sentenceComponents) {
        this.sentenceComponents = sentenceComponents;
    }

    @Override
    public String getText() {
        return  sentenceComponents.stream()
                .map(Component::getText)
                .collect(Collectors.joining(SPACE_SEPARATOR));
    }

    @Override
    public List<Component> getComponents() {
        return sentenceComponents;
    }
}
