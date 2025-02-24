package com.epam.jwd.model;

import java.util.List;

public class SymbolComponent implements LeafComponent {

    private String content;

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("Add is not allowed for Leaf component");
    }

    @Override
    public List<Component> getChild() {
        throw new UnsupportedOperationException("Get child is not allowed for Leaf component");
    }

    @Override
    public boolean hasChild() {
        return false;
    }

    @Override
    public String getName() {
        return "SymbolComponent";
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}
