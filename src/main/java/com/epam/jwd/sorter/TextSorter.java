package com.epam.jwd.sorter;

import com.epam.jwd.model.Component;
import java.util.Comparator;

public class TextSorter implements Sorter {

    private static final SentencesByLexemeLengthSorter nextSorter = SentencesByLexemeLengthSorter.getInstance();

    private static TextSorter instance;

    private TextSorter() {}

    public static TextSorter getInstance() {
        if (instance == null) {
            instance = new TextSorter();
        }
        return instance;
    }


    @Override
    public String sort(SorterVariations variation, OrderVariations orderVariation, Component component) {
        if(variation.equals(SorterVariations.PARAGRAPHS_BY_SENTENCES)) {
            component.getComponents().sort(getComparator(orderVariation));
        }
        else {
            nextSorter.sort(variation, orderVariation,component);
        }
        return component.getText();
    }

    private Comparator<Component> getComparator(OrderVariations order) {
        return switch (order) {
            case ASCENDING -> Comparator.comparingInt(c -> c.getComponents().size());
            case DESCENDING -> Comparator.comparingInt((Component c) -> c.getComponents().size()).reversed();
        };
    }
}
