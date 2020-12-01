package by.shibaev.university.model.dao;

import by.shibaev.university.model.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao<T> {

    public abstract List<T> findAll() throws DaoException;

    public abstract boolean add(T entity) throws DaoException;

    protected static final Logger logger = LogManager.getLogger();

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Statement wasn't close", e);
        }
    }
}
