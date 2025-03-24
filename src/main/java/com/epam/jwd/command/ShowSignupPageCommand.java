package com.epam.jwd.command;

import com.epam.jwd.factory.ResponseFactory;
import com.epam.jwd.factory.SimpleCommandResponseFactory;

import static com.epam.jwd.command.PagePathsRegistry.SIGNUP;


public class ShowSignupPageCommand implements Command {

    private final ResponseFactory responseFactory = SimpleCommandResponseFactory.getInstance();

    private ShowSignupPageCommand() {}

    private static class Holder {
        private static final ShowSignupPageCommand INSTANCE = new ShowSignupPageCommand();
    }

    static ShowSignupPageCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return responseFactory.createForwardResponse(SIGNUP.getPath());
    }
}