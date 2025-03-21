package com.epam.jwd.command;

public enum ParameterNameRegistry {
    LOGIN_PARAMETER_NAME("login"),
    PASSWORD_PARAMETER_NAME("password"),
    ;

    private final String name;

    ParameterNameRegistry(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
