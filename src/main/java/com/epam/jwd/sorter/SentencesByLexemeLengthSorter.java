package com.epam.jwd.sorter;

import com.epam.jwd.model.Component;
import java.util.Comparator;

public class SentencesByLexemeLengthSorter implements Sorter {

    private static SentencesByLexemeLengthSorter instance;

    private SentencesByLexemeLengthSorter() {}

    public static SentencesByLexemeLengthSorter getInstance() {
        if(instance == null) {
            instance = new SentencesByLexemeLengthSorter();
        }
        return instance;
    }

    @Override
    public String sort(SorterVariations variation, OrderVariations orderVariations, Component component) {
        component.getComponents()
                .forEach(p -> p.getComponents().forEach(s -> s.getComponents().sort(getComparator(orderVariations))));
        return component.getText();
    }

    private Comparator<Component> getComparator(OrderVariations order) {
        return switch (order) {
            case ASCENDING -> Comparator.comparingInt(c -> c.getText().length());
            case DESCENDING -> Comparator.comparingInt((Component c) -> c.getText().length()).reversed();
        };
    }
}
