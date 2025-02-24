package com.epam.jwd.model;

import java.util.ArrayList;
import java.util.List;

public class TextComponent implements Component {

    private final List<Component> textComponents = new ArrayList<>();

    @Override
    public void add(Component component) {
        textComponents.add(component);
    }

    @Override
    public List<Component> getChild() {
        return textComponents;
    }

    @Override
    public boolean hasChild() {
        return true;
    }

    @Override
    public String getName() {
        return "TextComponent";
    }
}
