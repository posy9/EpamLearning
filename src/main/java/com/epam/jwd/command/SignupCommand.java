package com.epam.jwd.command;

import com.epam.jwd.controller.ResponseFactory;
import com.epam.jwd.controller.SimpleCommandResponseFactory;
import com.epam.jwd.model.User;
import com.epam.jwd.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.epam.jwd.command.ParameterNameRegistry.LOGIN_PARAMETER_NAME;
import static com.epam.jwd.command.ParameterNameRegistry.PASSWORD_PARAMETER_NAME;
import static com.epam.jwd.command.ShowMainPageCommand.PATH_TO_MAIN_PAGE;
import static com.epam.jwd.command.ShowSignupPageCommand.PATH_TO_SIGNUP_PAGE;

public class SignupCommand implements Command {

    private static final Logger LOG = LogManager.getLogger(SignupCommand.class);
    private static final String ERROR_SIGNUP_ATTRIBUTE_NAME = "errorSignupMessage";
    private static final String  ERROR_SIGNUP_MESSAGE = "User with login %s already exists.";
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
        String login = commandRequest.getParameter(LOGIN_PARAMETER_NAME.getName());
        String password = commandRequest.getParameter(PASSWORD_PARAMETER_NAME.getName());
        User newUser = new User(login, password);
        try {
            Optional<User> createdUser = userService.createUser(newUser);
            if (createdUser.isPresent()) {
                LOG.trace("Created user: " + createdUser.get());
            } else {
                LOG.warn("Failed to create user: " + newUser);
                commandRequest.addRequestAttribute(ERROR_SIGNUP_ATTRIBUTE_NAME, String.format(ERROR_SIGNUP_MESSAGE, login));
                return responseFactory.createCommandResponse(PATH_TO_SIGNUP_PAGE);
            }
        } catch (InterruptedException e) {
            LOG.warn("Current thread was interrupted", e);
        }
       return responseFactory.createCommandResponse(PATH_TO_MAIN_PAGE);
    }
}
