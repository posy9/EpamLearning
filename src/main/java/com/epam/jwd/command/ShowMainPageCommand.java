package com.epam.jwd.command;

import com.epam.jwd.factory.ResponseFactory;
import com.epam.jwd.factory.SimpleCommandResponseFactory;

import static com.epam.jwd.command.PagePathsRegistry.MAIN;

public class ShowMainPageCommand implements Command {

    private final ResponseFactory responseFactory = SimpleCommandResponseFactory.getInstance();

    private ShowMainPageCommand() {}

    private static class Holder {
        private static final ShowMainPageCommand INSTANCE = new ShowMainPageCommand();
    }

    static ShowMainPageCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return responseFactory.createForwardResponse(MAIN.getPath());
    }
}
