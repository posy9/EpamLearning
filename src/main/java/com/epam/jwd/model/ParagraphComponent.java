package com.epam.jwd.model;

import java.util.ArrayList;
import java.util.List;

public class ParagraphComponent implements Component {

    private final List<Component> paragraphComponents = new ArrayList<>();

    @Override
    public void add(Component component) {
        paragraphComponents.add(component);

    }

    @Override
    public List<Component> getChild() {
        return paragraphComponents;
    }

    @Override
    public boolean hasChild() {
        return true;
    }

    @Override
    public String getName() {
        return "ParagraphComponent";
    }

}
