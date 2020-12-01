package by.shibaev.university.model.dao;

import by.shibaev.university.model.entity.Event;
import by.shibaev.university.model.entity.Student;
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

public class EventDao extends AbstractDao<Event> {
    //"SELECT e.id,e.theme,t.first_name,t.surname,t.teacher_login, t.teacher_password , e.money,e.specification  s.subject_id, s.title,s.hours
    // FROM event e INNER JOIN teacher t ON t.teacher_id = e.teacher_fk INNER JOIN subject s ON s.subject_id = t.subject_fk ";
    @Override
    public List<Event> findAll() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        List<Event> events = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(DataBaseInjection.FIND_ALL_EVENTS);
            ResultSet resultSet = statement.executeQuery();
            int id = resultSet.getInt(1);
            String theme = resultSet.getString(2);
            String name = resultSet.getString(3);
            String surname = resultSet.getString(4);
            String login = resultSet.getString(5);
            String password = resultSet.getString(6);
            int money = resultSet.getInt(7);
            String specification = resultSet.getString(8);
            int subjectId = resultSet.getInt(9);
            String title = resultSet.getString(10);
            int hours = resultSet.getInt(11);
            int teacherId = resultSet.getInt(12);
            Event e = new Event(id,theme,money,specification,new Teacher(teacherId,name,surname,new Subject(subjectId,title,hours),login,password));
            events.add(e);
            close(statement);
            logger.log(Level.INFO, "Got {} events", events.size());
        } catch (SQLException e) {
            throw new DaoException("Statement creation error", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return events;
    }

    @Override
    public boolean add(Event entity) throws DaoException {
        int i = 0;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.ADD_EVENT);
                statement.setString(1, entity.getTheme());
                statement.setInt(2, entity.getTeacher().getId());
                statement.setInt(3, entity.getEvent_grant());
                statement.setString(4, entity.getSpecification());
                i = statement.executeUpdate();
                close(statement);
                logger.log(Level.INFO, "Was added: {}", entity);
            } catch (SQLException e) {
                throw new DaoException("Statement creation error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return i != 0;
    }



    public List<Event> selectByTeacherAndStudent(Teacher teacher, Student student) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        List<Event> events = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(DataBaseInjection.FIND_EVENTS_BY_STUDENT_AND_TEACHER);
            statement.setInt(1,teacher.getId());
            statement.setInt(2,student.getId());
            ResultSet resultSet = statement.executeQuery();
            int id = resultSet.getInt(1);
            String theme = resultSet.getString(2);
            String name = resultSet.getString(3);
            String surname = resultSet.getString(4);
            String login = resultSet.getString(5);
            String password = resultSet.getString(6);
            int money = resultSet.getInt(7);
            String specification = resultSet.getString(8);
            int subjectId = resultSet.getInt(9);
            String title = resultSet.getString(10);
            int hours = resultSet.getInt(11);
            int teacherId = resultSet.getInt(12);
            Event e = new Event(id,theme,money,specification,new Teacher(teacherId,name,surname,new Subject(subjectId,title,hours),login,password));
            events.add(e);
            close(statement);
            logger.log(Level.INFO, "Got {} events", events.size());
        } catch (SQLException e) {
            throw new DaoException("Statement creation error", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return events;
    }

    public List<Event> selectByTeacher(Teacher teacher) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        List<Event> events = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(DataBaseInjection.FIND_EVENTS_BY_TEACHER);
            statement.setInt(1,teacher.getId());
            ResultSet resultSet = statement.executeQuery();
            int id = resultSet.getInt(1);
            String theme = resultSet.getString(2);
            String name = resultSet.getString(3);
            String surname = resultSet.getString(4);
            String login = resultSet.getString(5);
            String password = resultSet.getString(6);
            int money = resultSet.getInt(7);
            String specification = resultSet.getString(8);
            int subjectId = resultSet.getInt(9);
            String title = resultSet.getString(10);
            int hours = resultSet.getInt(11);
            int teacherId = resultSet.getInt(12);
            Event e = new Event(id,theme,money,specification,new Teacher(teacherId,name,surname,new Subject(subjectId,title,hours),login,password));
            events.add(e);
            close(statement);
            logger.log(Level.INFO, "Got {} events", events.size());
        } catch (SQLException e) {
            throw new DaoException("Statement creation error", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return events;
    }

    public List<Event> selectByStudent(Student student) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        List<Event> events = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(DataBaseInjection.FIND_EVENTS_BY_STUDENT);
            statement.setInt(1,student.getId());
            ResultSet resultSet = statement.executeQuery();
            int id = resultSet.getInt(1);
            String theme = resultSet.getString(2);
            String name = resultSet.getString(3);
            String surname = resultSet.getString(4);
            String login = resultSet.getString(5);
            String password = resultSet.getString(6);
            int money = resultSet.getInt(7);
            String specification = resultSet.getString(8);
            int subjectId = resultSet.getInt(9);
            String title = resultSet.getString(10);
            int hours = resultSet.getInt(11);
            int teacherId = resultSet.getInt(12);
            Event e = new Event(id,theme,money,specification,new Teacher(teacherId,name,surname,new Subject(subjectId,title,hours),login,password));
            events.add(e);
            close(statement);
            logger.log(Level.INFO, "Got {} events", events.size());
        } catch (SQLException e) {
            throw new DaoException("Statement creation error", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return events;
    }
}
