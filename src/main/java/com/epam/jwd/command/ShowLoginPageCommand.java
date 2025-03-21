package com.epam.jwd.command;

import com.epam.jwd.controller.ResponseFactory;
import com.epam.jwd.controller.SimpleCommandResponseFactory;

public class ShowLoginPageCommand implements Command {

    public static final String PATH_TO_LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
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
        return responseFactory.createCommandResponse(PATH_TO_LOGIN_PAGE);
    }
}
