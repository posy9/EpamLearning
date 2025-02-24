package com.epam.jwd.model;

import java.util.ArrayList;
import java.util.List;

public class SentenceComponent implements Component {

    private final List<Component> sentenceComponents = new ArrayList<>();

    @Override
    public void add(Component component) {
        sentenceComponents.add(component);
    }

    @Override
    public List<Component> getChild() {
        return sentenceComponents;
    }

    @Override
    public boolean hasChild() {
        return true;
    }

    @Override
    public String getName() {
        return "SentenceComponent";
    }
}
