package com.epam.jwd.controller;

import com.epam.jwd.command.CommandResponse;

public class SimpleCommandResponse implements CommandResponse {

    private final String path;
    private final boolean redirect;

    SimpleCommandResponse(String path, boolean redirect) {
        this.path = path;
        this.redirect = redirect;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public boolean isRedirect() {
        return redirect;
    }
}
