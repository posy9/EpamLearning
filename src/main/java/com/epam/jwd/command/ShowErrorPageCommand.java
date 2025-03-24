package com.epam.jwd.command;

import com.epam.jwd.factory.ResponseFactory;
import com.epam.jwd.factory.SimpleCommandResponseFactory;

import static com.epam.jwd.command.PagePathsRegistry.ERROR;

public class ShowErrorPageCommand implements Command {

    private final ResponseFactory responseFactory = SimpleCommandResponseFactory.getInstance();

    private ShowErrorPageCommand() {}

    private static class Holder {
        private static final ShowErrorPageCommand INSTANCE = new ShowErrorPageCommand();
    }

    static ShowErrorPageCommand getInstance() {
        return ShowErrorPageCommand.Holder.INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest commandRequest) {
        return responseFactory.createForwardResponse(ERROR.getPath());
    }

}
