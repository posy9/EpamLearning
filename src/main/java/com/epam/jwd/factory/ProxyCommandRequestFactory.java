package com.epam.jwd.factory;

import com.epam.jwd.command.CommandRequest;
import jakarta.servlet.http.HttpServletRequest;

public class ProxyCommandRequestFactory implements RequestFactory {

    private ProxyCommandRequestFactory(){};

    private static class Handler{
        private static final ProxyCommandRequestFactory INSTANCE = new ProxyCommandRequestFactory();
    }

    public static ProxyCommandRequestFactory getInstance(){
        return Handler.INSTANCE;
    }

    @Override
    public CommandRequest createCommandRequest(HttpServletRequest request) {
        return new ProxyCommandRequest(request);
    }
}
