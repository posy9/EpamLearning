package com.epam.jwd.controller;

import com.epam.jwd.command.CommandResponse;

public class SimpleCommandResponse implements CommandResponse {

    private final String path;

    SimpleCommandResponse(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
