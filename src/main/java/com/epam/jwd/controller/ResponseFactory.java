package com.epam.jwd.controller;

import com.epam.jwd.command.CommandResponse;

public interface ResponseFactory {

    CommandResponse createForwardResponse(String path);

    CommandResponse createRedirectResponse(String path);
}
