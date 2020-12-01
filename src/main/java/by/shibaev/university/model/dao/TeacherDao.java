package by.shibaev.university.model.dao;

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
import java.util.Optional;

public class TeacherDao extends AbstractDao<Teacher> {

    public static TeacherDao INSTANCE = new TeacherDao();

    private TeacherDao() {
    }

    //"SELECT teacher_id,teacher_login, teacher_password, first_name, surname,subject_fk FROM teacher";
    @Override
    public List<Teacher> findAll() throws DaoException {
        List<Teacher> teachers = new ArrayList<>();
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.FIND_ALL_TEACHER);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int teacherId = result.getInt(1);
                    String login = result.getString(2);
                    String password = result.getString(3);
                    String name = result.getString(4);
                    String surname = result.getString(5);
                    int subjectId = result.getInt(6);
                    String title = result.getString(7);
                    int hours = result.getInt(8);
                    Teacher teacher = new Teacher(teacherId,name,surname,new Subject(subjectId,title,hours),login,password);
                    teachers.add(teacher);
                }
                close(statement);
            } catch (SQLException e) {
                throw new DaoException("Statement TeacherDao findALl creation error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return teachers;
    }


    // "INSERT INTO student(first_name,teacher_login,teacher_password, surname, subject_fk) VALUES (? , ?, ? , ?);";
    @Override
    public boolean add(Teacher entity) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        int i = 0;
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.ADD_TEACHER);
                statement.setString(1, entity.getFirstName());
                statement.setString(2,entity.getLogin());
                statement.setString(3,entity.getPassword());
                statement.setString(4, entity.getSurname());
                statement.setInt(5,entity.getSubject().getId());
                i = statement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoException("Statement TeacherDao add creation error" + e, e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return i != 0;
    }

    public Optional<Teacher> login(String login, String password) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Optional<Teacher> teacher = Optional.empty();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.TEACHER_LOGIN);
                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet result = statement.executeQuery();
                if(result.next()){
                    int teacherId = result.getInt(1);
                    String name = result.getString(4);
                    String surname = result.getString(5);
                    int subjectId = result.getInt(6);
                    String title = result.getString(7);
                    int hours = result.getInt(8);
                    Teacher t = new Teacher(teacherId,name,surname,new Subject(subjectId,title,hours),login,password);
                    teacher = Optional.of(t);
                }
                close(statement);
            } catch (SQLException e) {
                throw new DaoException("Statement TeacherDao login creation error" + e, e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return teacher;
    }

}
