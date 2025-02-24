package com.epam.jwd.model;

import java.util.ArrayList;
import java.util.List;

public class LexemeComponent implements Component {

    private final List<Component> lexemeComponents = new ArrayList<>();

    @Override
    public void add(Component component) {
        lexemeComponents.add(component);
    }

    @Override
    public List<Component> getChild() {
        return lexemeComponents;
    }

    @Override
    public boolean hasChild() {
        return true;
    }

    @Override
    public String getName() {
        return "LexemeComponent";
    }
}
