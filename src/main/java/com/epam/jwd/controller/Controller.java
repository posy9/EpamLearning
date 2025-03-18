package com.epam.jwd.controller;

import java.io.*;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/controller")
public class Controller extends HttpServlet {

private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
       proceed(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {}

    private void proceed(HttpServletRequest request, HttpServletResponse response) {
        final String commandName = request.getParameter("command");
        Command command = Command.of(commandName);
        CommandResponse commandResponse = command.execute(null);
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(commandResponse.getPath());
            dispatcher.forward(request,response);

        } catch (ServletException e) {
            LOG.error("Servlet exception", e);
        } catch (IOException e) {
            LOG.error("IO exception", e);
        }
    }


}