package com.epam.jwd.controller;

import java.io.*;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandRequest;
import com.epam.jwd.command.CommandResponse;
import com.epam.jwd.dbconnection.ConnectionPool;
import com.epam.jwd.factory.RequestFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.epam.jwd.command.ParameterNameRegistry.COMMAND_PARAMETER_NAME;

@WebServlet("/controller")
public class Controller extends HttpServlet {


    private static final Logger LOG = LogManager.getLogger(Controller.class);

    private static final RequestFactory requestFactory = RequestFactory.getInstance();

    @Override
    public void init(){
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
    public void destroy(){
        ConnectionPool.instance().shutDown();
    }

    private static void process(HttpServletRequest request, HttpServletResponse response) {
        final String commandName = request.getParameter(COMMAND_PARAMETER_NAME.getName());
        Command command = Command.of(commandName);
        CommandRequest commandRequest = requestFactory.createCommandRequest(request);
        CommandResponse commandResponse = command.execute(commandRequest);
        try {
            if (commandResponse.isRedirect()){
                response.sendRedirect(commandResponse.getPath());
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(commandResponse.getPath());
                dispatcher.forward(request, response);
            }
        } catch (ServletException e) {
            LOG.error("Servlet exception", e);
        } catch (IOException e) {
            LOG.error("IO exception", e);
        }
    }

}