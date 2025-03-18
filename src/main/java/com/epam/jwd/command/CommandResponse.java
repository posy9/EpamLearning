package com.epam.jwd.command;

public interface CommandResponse {

     boolean isRedirect();

     String getPath();
}
