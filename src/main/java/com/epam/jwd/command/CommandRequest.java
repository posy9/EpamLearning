package com.epam.jwd.command;

import com.epam.jwd.model.User;

import java.util.Optional;

public interface CommandRequest {

    void addRequestAttribute(String name, Object attribute);

    String getParameter(String name);

    void clearSession();

    void createSession();

    void addSessionAttribute(String name, Object attribute);

    boolean sessionExists();

    Optional<Object> retrieveFromSession(String nameOfAttribute);

    Object getSessionAttribute(String name);
}
