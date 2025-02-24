package com.epam.jwd.action;

import com.epam.jwd.model.TextComponent;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComponentSorter {

    private static ComponentSorter instance;

    private ComponentSorter() {}

    public static ComponentSorter getInstance() {
        if (instance == null) {
            instance = new ComponentSorter();
        }
        return instance;
    }

    public List<String> paragraphsBySentencesSort(TextComponent component) {
        TextMerger textMerger = TextMerger.getInstance();
        return component.getChild().stream()
                .sorted(Comparator.comparingInt(c -> c.getChild().size()))
                .map(textMerger::mergeText)
                .collect(Collectors.toList());
    }
}