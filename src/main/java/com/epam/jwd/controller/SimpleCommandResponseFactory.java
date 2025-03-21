package com.epam.jwd.controller;

import com.epam.jwd.command.CommandResponse;

import java.util.HashMap;
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

    private final Map<String, CommandResponse> existingResponses= new ConcurrentHashMap<>();

    @Override
    public CommandResponse createCommandResponse(String path) {
        return existingResponses.computeIfAbsent(path, k -> new SimpleCommandResponse(path));
    }
}
