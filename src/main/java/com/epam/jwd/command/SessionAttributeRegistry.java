package com.epam.jwd.command;

public enum SessionAttributeRegistry {
    USER("user"),
    NUMBER_OF_PAGES("numberOfPages"),
    CURRENT_PAGE("currentPage"),
    ;

    private final String name;

    SessionAttributeRegistry(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
