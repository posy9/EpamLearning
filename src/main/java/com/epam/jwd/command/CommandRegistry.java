package com.epam.jwd.command;

public enum CommandRegistry {
    SHOW_MAIN_PAGE(ShowMainPageCommand.INSTANCE, "main_page"),
    SHOW_MEDICAMENT_PAGE(ShowMedicamentPageCommand.INSTANCE, "medicament_page"),
    DEFAULT(ShowMainPageCommand.INSTANCE, "");

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
