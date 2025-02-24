package com.epam.jwd.action;

import com.epam.jwd.model.Component;
import com.epam.jwd.model.LeafComponent;

public class TextMerger {

    private static TextMerger instance;
    private static final String SEPARATOR_FOR_CHILD_ELEMENTS = " ";

    private TextMerger() {}

    public static TextMerger getInstance() {
        if (instance == null) {
            instance = new TextMerger();
        }
        return instance;
    }

    public String mergeText(Component component) {
        StringBuilder content = new StringBuilder();
        if (component.hasChild()) {
            if(component.getName().equals("LexemeComponent") || component.getName().equals("ParagraphComponent")) {
                component.getChild().forEach(child -> {
                    content.append(mergeText(child));
                });
            }
            else if(component.getName().equals("TextComponent")) {
                component.getChild().forEach(child -> {
                    content.append("\t").append(mergeText(child)).append("\n");
                });
            }
            else {
                component.getChild().forEach(child -> {
                    content.append(mergeText(child)).append(SEPARATOR_FOR_CHILD_ELEMENTS);
                });
            }
        } else {
            LeafComponent childComponent = (LeafComponent) component;
            content.append(childComponent.getContent());
        }
        return content.toString();
    }
}
