package by.shibaev.university.model.dao;

import by.shibaev.university.model.entity.Student;
import by.shibaev.university.model.exception.DaoException;
import by.shibaev.university.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDao extends AbstractDao<Student> {

    public static final StudentDao INSTANCE = new StudentDao();

    private StudentDao() {
    }

    @Override
    public List<Student> findAll() throws DaoException {
        List<Student> students = new ArrayList<>();
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.FIND_ALL_STUDENTS);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int id = result.getInt(1);
                    String login = result.getString(2);
                    String password = result.getString(3);
                    String name = result.getString(4);
                    String surname = result.getString(5);
                    int group = result.getInt(6);
                    int grant = result.getInt(7);
                    int mark = result.getInt(8);
                    Student student = new Student(name, surname,password,login, group, mark, grant, id);
                    students.add(student);
                }
                close(statement);
            } catch (SQLException e) {
                throw new DaoException("Statement creation error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return students;
    }

    public List<Student> findBySubject() throws DaoException {
        List<Student> students = new ArrayList<>();
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.FIND_ALL_STUDENTS);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int id = result.getInt(1);
                    String login = result.getString(2);
                    String password = result.getString(3);
                    String name = result.getString(4);
                    String surname = result.getString(5);
                    int group = result.getInt(6);
                    int grant = result.getInt(7);
                    int mark = result.getInt(8);
                    Student student = new Student(name, surname,password,login, group, mark, grant, id);
                    students.add(student);
                }
                close(statement);
            } catch (SQLException e) {
                throw new DaoException("Statement creation error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return students;
    }


    //"INSERT INTO student(first_name, surname, student_group,student_login, student_password) VALUES (?, ? , ?, ?, ?);"
    @Override
    public boolean add(Student entity) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        int i = 0;
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.ADD_STUDENT);
                statement.setString(1, entity.getFirstName());
                statement.setString(2, entity.getSurname());
                statement.setInt(3, entity.getStudentGroup());
                statement.setString(4,entity.getLogin());
                statement.setString(5,entity.getPassword());
                i = statement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoException("Statement creation error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return i != 0;
    }

    public Optional<Student> selectStudentById(int id) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Optional<Student> student = Optional.empty();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.FIND_STUDENT_BY_ID);
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    String login = result.getString(1);
                    String password = result.getString(2);
                    String name = result.getString(3);
                    String surname = result.getString(4);
                    int group = result.getInt(5);
                    int grant = result.getInt(6);
                    int mark = result.getInt(7);
                    Student s = new Student(name, surname,password,login, group, mark, grant, id);
                    student = Optional.of(s);
                }
                close(statement);
            } catch (SQLException e) {
                throw new DaoException("Statement creation error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return student;
    }

    public Optional<Student> login(String login, String password) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Optional<Student> student = Optional.empty();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.STUDENT_LOGIN);
                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet result = statement.executeQuery();
                if(result.next()){
                    int id = result.getInt(1);
                    String name = result.getString(2);
                    String surname = result.getString(3);
                    int group = result.getInt(4);
                    int grant = result.getInt(5);
                    int mark = result.getInt(6);
                    Student s = new Student(name, surname,password,login, group, mark, grant, id);
                    student = Optional.of(s);
                }
                close(statement);
            } catch (SQLException e) {
                throw new DaoException("Statement creation error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return student;
    }
}
