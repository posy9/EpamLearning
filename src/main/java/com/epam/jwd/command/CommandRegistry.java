package com.epam.jwd.command;

public enum CommandRegistry {
    SHOW_MAIN_PAGE(ShowMainPageCommand.getInstance(), "show_main"),
    SHOW_MEDICAMENT_PAGE(ShowMedicamentPageCommand.getInstance(), "show_medicament"),
    SHOW_LOGIN_PAGE(ShowLoginPageCommand.getInstance(), "show_login"),
    LOGIN(LoginCommand.getInstance(), "login"),
    LOGOUT(LogoutCommand.getInstance(),"logout"),
    SHOW_SIGNUP_PAGE(ShowSignupPageCommand.getInstance(), "show_signup"),
    SIGNUP(SignupCommand.getInstance(), "signup"),
    DEFAULT(ShowMainPageCommand.getInstance(), "");


    private final Command command;
    private final String path;

    CommandRegistry(Command command, String path) {
        this.command = command;
        this.path = path;
    }

    static Command of(String name) {
        for (CommandRegistry commandRegistry : CommandRegistry.values()) {
            if (commandRegistry.path.equalsIgnoreCase(name)){
                return commandRegistry.command;
            }
        }
        return DEFAULT.command;
    }
}
