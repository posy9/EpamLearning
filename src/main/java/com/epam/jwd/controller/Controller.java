package com.epam.jwd.controller;

import java.io.*;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandRequest;
import com.epam.jwd.command.CommandResponse;
import com.epam.jwd.dbconnection.ConnectionPool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final String COMMAND_PARAM_NAME = "command";
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    private static final RequestFactory requestFactory = RequestFactory.getInstance();

    @Override
    public void init() {
        LOG.trace("initializing controller");
        ConnectionPool.instance().init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    @Override
    public void destroy() {
        LOG.trace("destroying controller");
        ConnectionPool.instance().shutDown();
    }

    private static void process(HttpServletRequest request, HttpServletResponse response) {
        final String commandName = request.getParameter(COMMAND_PARAM_NAME);
        Command command = Command.of(commandName);
        CommandRequest commandRequest = requestFactory.createCommandRequest(request);
        CommandResponse commandResponse = command.execute(commandRequest);
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(commandResponse.getPath());
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            LOG.error("Servlet exception", e);
        } catch (IOException e) {
            LOG.error("IO exception", e);
        }
    }

}