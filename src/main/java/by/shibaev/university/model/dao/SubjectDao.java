package by.shibaev.university.model.dao;

import by.shibaev.university.model.entity.Subject;
import by.shibaev.university.model.entity.Teacher;
import by.shibaev.university.model.exception.DaoException;
import by.shibaev.university.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao extends AbstractDao<Subject> {

    public static final SubjectDao INSTANCE = new SubjectDao();

    private SubjectDao() {
    }
    @Override
    public List<Subject> findAll() throws DaoException {
        List<Subject> subjects = new ArrayList<>();
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.FIND_ALL_SUBJECT);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int id = result.getInt(1);
                    String title = result.getString(2);
                    int hours = result.getInt(3);
                    subjects.add(new Subject(id,title,hours));
                }
                close(statement);
            } catch (SQLException e) {
                throw new DaoException("Statement SubjectDao creation error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return subjects;
    }

    @Override
    public boolean add(Subject entity) throws DaoException {
        int i = 0;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.ADD_SUBJECT);
                statement.setString(1, entity.getTitle());
                statement.setInt(2, entity.getHours());
                i = statement.executeUpdate();
                close(statement);
                logger.log(Level.INFO, "Was added: {}", entity);
            } catch (SQLException e) {
                throw new DaoException("Statement SubjectDao creation error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return i != 0;
    }
}
