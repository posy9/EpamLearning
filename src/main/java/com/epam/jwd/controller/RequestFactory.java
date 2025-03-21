package com.epam.jwd.controller;

import com.epam.jwd.command.CommandRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface RequestFactory {

    CommandRequest createCommandRequest(HttpServletRequest request);

    static RequestFactory getInstance() {
        return ProxyCommandRequestFactory.getInstance();
    }
}
