package com.epam.jwd.command;

import com.epam.jwd.controller.ResponseFactory;
import com.epam.jwd.controller.SimpleCommandResponseFactory;
import com.epam.jwd.model.User;
import com.epam.jwd.service.UserService;

import java.util.Optional;

import static com.epam.jwd.command.ParameterNameRegistry.LOGIN_PARAMETER_NAME;
import static com.epam.jwd.command.ParameterNameRegistry.PASSWORD_PARAMETER_NAME;
import static com.epam.jwd.command.ShowMainPageCommand.PATH_TO_MAIN_PAGE;


public class LoginCommand implements Command {

    private static final String ERROR_LOGIN_PASS_ATTRIBUTE_NAME = "errorLoginPassMessage";
    private static final String ERROR_LOGIN_PASS_ATTRIBUTE_VALUE = "Wrong password or login";
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";

    private final ResponseFactory responseFactory = SimpleCommandResponseFactory.getInstance();

    private final UserService userService = new UserService();

    private LoginCommand() {}

    private static class Holder {
        private static final LoginCommand INSTANCE = new LoginCommand();
    }

    static LoginCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest commandRequest) {
        if (commandRequest.sessionExists() && commandRequest.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME).isPresent()) {
            return responseFactory.createCommandResponse(PATH_TO_MAIN_PAGE);
        }
        final String login = commandRequest.getParameter(LOGIN_PARAMETER_NAME.getName());
        final String password = commandRequest.getParameter(PASSWORD_PARAMETER_NAME.getName());
        final Optional<User> user;
        try {
            user = userService.authenticate(login, password);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (user.isEmpty()) {
            commandRequest.addRequestAttribute(ERROR_LOGIN_PASS_ATTRIBUTE_NAME, ERROR_LOGIN_PASS_ATTRIBUTE_VALUE);
            return responseFactory.createCommandResponse(ShowLoginPageCommand.PATH_TO_LOGIN_PAGE);
        }
        commandRequest.createSession();
        commandRequest.addSessionAttribute(USER_SESSION_ATTRIBUTE_NAME, user.get());
        return responseFactory.createCommandResponse(ShowMainPageCommand.PATH_TO_MAIN_PAGE);
    }
}
