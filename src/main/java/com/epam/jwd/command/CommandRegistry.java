package com.epam.jwd.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandRegistry {
    SHOW_MAIN_PAGE_COMMAND(ShowMainPageCommand.getInstance(), "show_main"),
    SHOW_MEDICAMENT_PAGE_COMMAND(ShowMedicamentPageCommand.getInstance(), "show_medicament"),
    SHOW_LOGIN_PAGE_COMMAND(ShowLoginPageCommand.getInstance(), "show_login"),
    LOGIN_COMMAND(LoginCommand.getInstance(), "login"),
    LOGOUT_COMMAND(LogoutCommand.getInstance(),"logout"),
    SHOW_SIGNUP_PAGE(ShowSignupPageCommand.getInstance(), "show_signup"),
    SIGNUP_COMMAND(SignupCommand.getInstance(), "signup"),
    ERROR_COMMAND(ShowErrorPageCommand.getInstance(), "show_error");

    private static final Logger LOG = LogManager.getLogger(CommandRegistry.class);
    private static final String SEARCHING_FOR_COMMAND_MSG = "Searching for command {}";
    private static final String FOUND_COMMAND_MSG = "Found command {}";
    private static final String COMMAND_NOT_FOUND_MSG = "Command {} not found";

    private final Command command;
    private final String path;

    CommandRegistry(Command command, String path) {
        this.command = command;
        this.path = path;
    }

    static Command of(String name) {
        LOG.trace(SEARCHING_FOR_COMMAND_MSG, name);
        for (CommandRegistry commandRegistry : CommandRegistry.values()) {
            if (commandRegistry.path.equalsIgnoreCase(name)){
                LOG.trace(FOUND_COMMAND_MSG, name);
                return commandRegistry.command;
            }
        }
        LOG.warn(COMMAND_NOT_FOUND_MSG, name);
        return ERROR_COMMAND.command;
    }
}
