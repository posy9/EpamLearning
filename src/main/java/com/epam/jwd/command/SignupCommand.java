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
import static com.epam.jwd.command.RequestAttributeRegistry.ERROR_SIGNUP;
import static com.epam.jwd.command.SessionAttributeRegistry.USER;

public class SignupCommand implements Command {

    private static final Logger LOG = LogManager.getLogger(SignupCommand.class);
    private static final String SUCCESSFUL_CREATION_MSG = "Created user: {}";
    private static final String CREATION_FAILURE_MSG = "Failed to create user: {}";
    private final ResponseFactory responseFactory = SimpleCommandResponseFactory.getInstance();
    private final UserService userService = new UserService();

    private SignupCommand() {}

    private static class Holder {
        private static final SignupCommand INSTANCE = new SignupCommand();
    }

    static SignupCommand getInstance() {
        return SignupCommand.Holder.INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest commandRequest) {
        if(!isAuthorized(commandRequest)) {
            return responseFactory.createForwardResponse(ERROR.getPath());
        }
        String login = commandRequest.getParameter(LOGIN_PARAMETER_NAME.getName());
        String password = commandRequest.getParameter(PASSWORD_PARAMETER_NAME.getName());
        User newUser = new User(login, password);
        Optional<User> createdUser = userService.createUser(newUser);
        if (createdUser.isPresent()) {
            LOG.trace(SUCCESSFUL_CREATION_MSG, createdUser.get());
        } else {
            LOG.error(CREATION_FAILURE_MSG, newUser);
            commandRequest.addRequestAttribute(ERROR_SIGNUP.getName(), login);
            return responseFactory.createForwardResponse(SIGNUP.getPath());
        }
       return responseFactory.createRedirectResponse(INDEX.getPath());
    }

    private boolean isAuthorized(CommandRequest request) {
        return request.sessionExists() && request.getSessionAttribute(USER.getName()) != null;
    }
}
