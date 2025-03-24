package com.epam.jwd.factory;

import com.epam.jwd.command.CommandRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface RequestFactory {

    CommandRequest createCommandRequest(HttpServletRequest request);

    static RequestFactory getInstance() {
        return ProxyCommandRequestFactory.getInstance();
    }
}
