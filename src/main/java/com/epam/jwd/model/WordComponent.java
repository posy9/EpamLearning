package com.epam.jwd.model;

import java.util.List;

public class WordComponent implements Component {

    private final String content;

    public WordComponent(String content) {
        this.content = content;
    }

    @Override
    public String getText() {
        return content;
    }

    @Override
    public List<Component> getComponents() {
        throw new UnsupportedOperationException("Leaf component does not have components");
    }
}

