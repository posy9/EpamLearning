package com.epam.jwd.command;

import com.epam.jwd.controller.ResponseFactory;
import com.epam.jwd.controller.SimpleCommandResponseFactory;

public class ShowSignupPageCommand implements Command {

    public static final String PATH_TO_SIGNUP_PAGE = "/WEB-INF/jsp/signup.jsp";

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
        return responseFactory.createCommandResponse(PATH_TO_SIGNUP_PAGE);
    }
}