package com.epam.jwd.dbconnection;

import com.epam.jwd.exception.CouldNotInitializeConnectionPoolError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ConcurrentConnectionPool implements ConnectionPool {
    private static final Logger LOG = LogManager.getLogger(ConcurrentConnectionPool.class);

    private static final int INITIAL_CONNECTIONS_AMOUNT = 8;
    private static final String DB_URL = System.getenv("DB_LINK");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    private final Queue<ProxyConnection> availableConnections = new ArrayDeque<>();
    private final List<ProxyConnection> givenAwayConnections = new ArrayList<>();

    private final Lock lock = new ReentrantLock();
    private final Condition connectionAvailable = lock.newCondition();

    private boolean initialized = false;

    private ConcurrentConnectionPool() {}

    private static class Holder {
        private static final ConcurrentConnectionPool INSTANCE = new ConcurrentConnectionPool();
    }

    public static ConcurrentConnectionPool getInstance() {
        return ConcurrentConnectionPool.Holder.INSTANCE;
    }

    @Override
    public boolean isInitialized() {
        lock.lock();
        try {
            return initialized;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean init() {
        lock.lock();
        try {
            if (!initialized) {
                initializeConnections(INITIAL_CONNECTIONS_AMOUNT);
                initialized = true;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean shutDown() {
        lock.lock();
        try {
            if (initialized) {
                closeConnections();
                deregisterDrivers();
                initialized = false;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Connection takeConnection() throws InterruptedException {
        lock.lock();
        try {
            while (availableConnections.isEmpty()) {
                connectionAvailable.await();
            }
            ProxyConnection connection = availableConnections.poll();
            givenAwayConnections.add(connection);
            return connection;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void returnConnection(Connection connection) {
        lock.lock();
        try {
            if (givenAwayConnections.remove(connection)) {
                availableConnections.add((ProxyConnection) connection);
                connectionAvailable.signal();
            } else {
                LOG.warn("Attempt to add unknown connection to Connection Pool. Connection: {}", connection);
            }
        } finally {
            lock.unlock();
        }
    }

    private void initializeConnections(int amount) {
        try {
            for (int i = 0; i < amount; i++) {
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                LOG.info("Initialized connection {}", conn);
                ProxyConnection proxyConnection = new ProxyConnection(conn, this);
                availableConnections.add(proxyConnection);
            }
        } catch (SQLException e) {
            LOG.error("Error occurred creating Connection");
            throw new CouldNotInitializeConnectionPoolError("Failed to create Connection", e);
        }
    }

    private static void deregisterDrivers() {
        LOG.trace("Unregistering SQL drivers");
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOG.error("Could not deregister driver", e);
            }
        }
    }

    private void closeConnections() {
        closeConnections(availableConnections);
        closeConnections(givenAwayConnections);
    }

    private void closeConnections(Collection<ProxyConnection> connections) {
        for (ProxyConnection conn : connections) {
            closeConnection(conn);
        }
    }

    private void closeConnection(ProxyConnection conn) {
        try {
            conn.realClose();
            LOG.info("Closed connection {}", conn);
        } catch (SQLException e) {
            LOG.error("Could not close connection", e);
        }
    }
}