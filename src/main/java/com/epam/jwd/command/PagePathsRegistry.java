package com.epam.jwd.command;

public enum PagePathsRegistry {
    INDEX("/"),
    MAIN("/WEB-INF/jsp/main.jsp"),
    MEDICAMENT("/WEB-INF/jsp/medicament.jsp"),
    LOGIN("/WEB-INF/jsp/login.jsp"),
    SIGNUP("/WEB-INF/jsp/signup.jsp"),
    ERROR("/WEB-INF/jsp/error.jsp");

    private final String path;

    PagePathsRegistry(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static PagePathsRegistry of(String name) {
        for (PagePathsRegistry page : values()) {
            if (page.name().equalsIgnoreCase(name)) {
                return page;
            }
        }
        return MAIN;
    }
}
