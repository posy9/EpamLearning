package com.epam.jwd.exception;

public class CouldNotInitializeConnectionPoolError extends Error {
    public CouldNotInitializeConnectionPoolError(String message, Throwable cause) {
        super(message, cause);
    }
}
