package com.epam.jwd.controller;

import com.epam.jwd.command.CommandResponse;

public interface ResponseFactory {

    CommandResponse createCommandResponse(String path);
}
