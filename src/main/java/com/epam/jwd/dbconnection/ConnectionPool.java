package com.epam.jwd.dbconnection;

import com.epam.jwd.exception.CouldNotInitializeConnectionPoolError;

import java.sql.Connection;

public interface ConnectionPool {
    static ConnectionPool instance() {
        return ConcurrentConnectionPool.getInstance();
    }

    boolean isInitialized();

    boolean init() throws CouldNotInitializeConnectionPoolError;

    boolean shutDown();

    Connection takeConnection() throws InterruptedException;

    void returnConnection(Connection connection);
}
