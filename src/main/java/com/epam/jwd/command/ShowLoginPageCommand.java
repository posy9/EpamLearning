package com.epam.jwd.command;

import com.epam.jwd.controller.ResponseFactory;
import com.epam.jwd.controller.SimpleCommandResponseFactory;

import static com.epam.jwd.command.PagePathsRegistry.LOGIN;

public class ShowLoginPageCommand implements Command {

    private final ResponseFactory responseFactory = SimpleCommandResponseFactory.getInstance();

    private ShowLoginPageCommand() {}

    private static class Holder {
        private static final ShowLoginPageCommand INSTANCE = new ShowLoginPageCommand();
    }

    static ShowLoginPageCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return responseFactory.createForwardResponse(LOGIN.getPath());
    }
}
