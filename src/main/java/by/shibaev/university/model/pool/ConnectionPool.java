package by.shibaev.university.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    INSTANCE;

    private BlockingDeque<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;
    private Logger logger = LogManager.getLogger();

    private final static int POOL_SIZE = 32;

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();
        Connection connection;
        try {
            Class.forName(ConnectionProperties.DRIVER);
            for (int i = 0; i < POOL_SIZE; i++) {
                connection = DriverManager.getConnection(ConnectionProperties.URL, ConnectionProperties.USERNAME, ConnectionProperties.PASSWORD);
                freeConnections.add(new ProxyConnection(connection));
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.FATAL, "Connection doesn't active", e);
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.WARN, "Get connection Error", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            givenAwayConnections.remove(connection);
            freeConnections.add((ProxyConnection) connection);
        }
        else{
            logger.log(Level.WARN, "unexpected connection");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().fullClose();
            } catch (SQLException | InterruptedException e) {
                logger.log(Level.WARN, "connection wasn't close", e);
            }
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Driver deregister error");
            }
        });
    }
}
