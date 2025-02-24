package com.epam.jwd.factory;

import com.epam.jwd.exception.IllegalNameOfComponentException;
import com.epam.jwd.model.*;

public class LeafComponentFactory {

    private static LeafComponentFactory instance;

    private LeafComponentFactory() {}

    public static LeafComponentFactory getInstance() {
        if (instance == null) {
            instance = new LeafComponentFactory();
        }
        return instance;
    }

    public LeafComponent newInstanceOf(String context) {
        return switch (context) {
            case "WordComponent" -> new WordComponent();
            case "BitExpressionComponent" -> new BitExpressionComponent();
            case "SymbolComponent" -> new SymbolComponent();
            default -> throw new IllegalNameOfComponentException("Illegal context " + context);
        };
    }
}
