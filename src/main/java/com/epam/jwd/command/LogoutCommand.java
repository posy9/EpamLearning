package com.epam.jwd.command;

import com.epam.jwd.controller.ResponseFactory;
import com.epam.jwd.controller.SimpleCommandResponseFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.epam.jwd.command.PagePathsRegistry.INDEX;
import static com.epam.jwd.command.SessionAttributeRegistry.USER;

public class LogoutCommand implements Command {

    private static final Logger LOG = LogManager.getLogger(LogoutCommand.class);
    public static final String LOG_OUT_FAILURE_MSG = "User does not exist";
    public static final String LOG_OUT_SUCCESSFUL_MSG = "User logged out successfully";

    private final ResponseFactory responseFactory = SimpleCommandResponseFactory.getInstance();

    private LogoutCommand() {}

    private static class Holder {
        private static final LogoutCommand INSTANCE = new LogoutCommand();
    }

    static LogoutCommand getInstance() {
        return LogoutCommand.Holder.INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest commandRequest) {
        if (noLoggedInUserPresent(commandRequest)) {
            LOG.error(LOG_OUT_FAILURE_MSG);
            //todo: error - no user found cannot logout
            return null;
        }
        commandRequest.clearSession();
        LOG.trace(LOG_OUT_SUCCESSFUL_MSG);
        return responseFactory.createRedirectResponse(INDEX.getPath());
    }

    private boolean noLoggedInUserPresent(CommandRequest request) {
        return !request.sessionExists() || (
                request.sessionExists()
                        && request.retrieveFromSession(USER.getName())
                        .isEmpty()
        );
    }
}
