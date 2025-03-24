package com.epam.jwd.command;

import com.epam.jwd.factory.ResponseFactory;
import com.epam.jwd.factory.SimpleCommandResponseFactory;
import com.epam.jwd.model.User;
import com.epam.jwd.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.epam.jwd.command.PagePathsRegistry.*;
import static com.epam.jwd.command.ParameterNameRegistry.LOGIN_PARAMETER_NAME;
import static com.epam.jwd.command.ParameterNameRegistry.PASSWORD_PARAMETER_NAME;
import static com.epam.jwd.command.RequestAttributeRegistry.ERROR_LOGIN_PASSWORD;
import static com.epam.jwd.command.SessionAttributeRegistry.USER;

public class LoginCommand implements Command {

    private static final String ERROR_LOGIN_PASS_ATTRIBUTE_VALUE = "Wrong password or login";
    private static final String USER_ALREADY_LOGGED_IN_MSG = "User already logged in";
    private static final String USER_AUTHENTICATION_MSG = "Authenticating user";
    private static final String USER_NOT_AUTHENTICATED_MSG = "User not authenticated";
    private static final Logger LOG = LogManager.getLogger(LoginCommand.class);

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

        if (commandRequest.sessionExists() && commandRequest.retrieveFromSession(USER.getName()).isPresent()) {
            LOG.error(USER_ALREADY_LOGGED_IN_MSG);
            return responseFactory.createForwardResponse(ERROR.getPath());
        }

        String login = commandRequest.getParameter(LOGIN_PARAMETER_NAME.getName());
        String password = commandRequest.getParameter(PASSWORD_PARAMETER_NAME.getName());

        LOG.trace(USER_AUTHENTICATION_MSG);
        Optional<User> user = userService.authenticate(login, password);

        if (user.isEmpty()) {
            LOG.error(USER_NOT_AUTHENTICATED_MSG);
            commandRequest.addRequestAttribute(ERROR_LOGIN_PASSWORD.getName(), ERROR_LOGIN_PASS_ATTRIBUTE_VALUE);
            return responseFactory.createForwardResponse(LOGIN.getPath());
        }
        commandRequest.createSession();
        commandRequest.addSessionAttribute(USER.getName(), user.get());
        return responseFactory.createRedirectResponse(INDEX.getPath());
    }
}
