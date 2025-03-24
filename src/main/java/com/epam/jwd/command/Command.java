package com.epam.jwd.command;

public interface Command {

    CommandResponse execute(CommandRequest commandRequest);

    static Command of(String commandName) {
        return CommandRegistry.of(commandName);
    }

}
