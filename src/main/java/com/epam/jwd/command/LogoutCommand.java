package com.epam.jwd.command;

import com.epam.jwd.controller.ResponseFactory;
import com.epam.jwd.controller.SimpleCommandResponseFactory;

import static com.epam.jwd.command.ShowMainPageCommand.PATH_TO_MAIN_PAGE;

public class LogoutCommand implements Command {

    private final ResponseFactory responseFactory = SimpleCommandResponseFactory.getInstance();

    private LogoutCommand() {}

    private static class Holder {
        private static final LogoutCommand INSTANCE = new LogoutCommand();
    }

    static LogoutCommand getInstance() {
        return LogoutCommand.Holder.INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest commandRequest) {
        commandRequest.clearSession();
        return responseFactory.createCommandResponse(PATH_TO_MAIN_PAGE);
    }
}
