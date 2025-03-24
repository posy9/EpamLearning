package com.epam.jwd.command;

public enum RequestAttributeRegistry {
    ERROR_LOGIN_PASSWORD("errorLoginPassMessage"),
    MEDICAMENT_TO_SHOW("medicamentToShow"),
    ERROR_SIGNUP("errorSignupLogin"),;

    private final String name;

    RequestAttributeRegistry(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
