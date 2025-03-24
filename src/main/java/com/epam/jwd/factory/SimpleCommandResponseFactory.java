package com.epam.jwd.factory;

import com.epam.jwd.command.CommandResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleCommandResponseFactory implements ResponseFactory {

    private SimpleCommandResponseFactory() {}

    private static class Holder {
        private static final SimpleCommandResponseFactory INSTANCE = new SimpleCommandResponseFactory();
    }

    public static SimpleCommandResponseFactory getInstance() {
        return Holder.INSTANCE;
    }

    private final Map<String, CommandResponse> existingForwardResponses= new ConcurrentHashMap<>();
    private final Map<String, CommandResponse> existingRedirectResponses= new ConcurrentHashMap<>();


    @Override
    public CommandResponse createRedirectResponse(String path) {
        return existingRedirectResponses.computeIfAbsent(path, k -> new SimpleCommandResponse(path,true));
    }

    @Override
    public CommandResponse createForwardResponse(String path) {
        return existingForwardResponses.computeIfAbsent(path, k -> new SimpleCommandResponse(path,false));
    }
}
