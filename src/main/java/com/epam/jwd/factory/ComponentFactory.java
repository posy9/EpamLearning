package com.epam.jwd.factory;

import com.epam.jwd.exception.IllegalNameOfComponentException;
import com.epam.jwd.model.*;

public class ComponentFactory {

    private static ComponentFactory instance;

    private ComponentFactory() {}

    public static ComponentFactory getInstance() {
        if (instance == null) {
            instance = new ComponentFactory();
        }
        return instance;
    }

    public Component newInstanceOf(String context) {
        return switch (context) {
            case "TextComponent" -> new TextComponent();
            case "SentenceComponent" -> new SentenceComponent();
            case "ParagraphComponent" -> new ParagraphComponent();
            case "LexemeComponent" -> new LexemeComponent();
            default -> throw new IllegalNameOfComponentException("Illegal context " + context);
        };
    }
}
