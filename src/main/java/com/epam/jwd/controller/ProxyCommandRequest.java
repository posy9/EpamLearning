package com.epam.jwd.controller;

import com.epam.jwd.command.CommandRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class ProxyCommandRequest implements CommandRequest {

    private final HttpServletRequest request;

    ProxyCommandRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void addRequestAttribute(String name, Object attribute) {
        request.setAttribute(name, attribute);
    }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }

    @Override
    public void clearSession() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public void createSession() {
        request.getSession(true);
    }

    @Override
    public void addSessionAttribute(String name, Object attribute) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(name, attribute);
        }
    }

    @Override
    public boolean sessionExists() {
        return request.getSession(false)!=null;
    }

    @Override
    public Optional<Object> retrieveFromSession(String nameOfSessionAttribute) {
        return  Optional.ofNullable(request.getSession(false).getAttribute(nameOfSessionAttribute));
    }

    @Override
    public Object getSessionAttribute(String name) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return session.getAttribute(name);
        }
        return null;
    }
}
