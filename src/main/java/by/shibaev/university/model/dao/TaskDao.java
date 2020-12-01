package by.shibaev.university.model.dao;

import by.shibaev.university.model.entity.Student;
import by.shibaev.university.model.entity.Subject;
import by.shibaev.university.model.entity.Task;
import by.shibaev.university.model.exception.DaoException;
import by.shibaev.university.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//t.mark, t.subject_task_id, t.task_text, t.subject_fk, s.student_id, s.surname, s.first_name,
// s.student_group, s.student_grant, s.average_mark,s.student_password,s.student_login
public class TaskDao extends AbstractDao<Task> {

    public static final TaskDao INSTANCE = new TaskDao();

    private TaskDao(){}

    public boolean updateTask(int id, String text, int mark) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        int i = 0;
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.UPDATE_STUDENT_TASK);
                statement.setString(1, text);
                statement.setInt(2, mark);
                statement.setInt(3, id);
                i = statement.executeUpdate();
                close(statement);
            } catch (SQLException e) {
                throw new DaoException("Statement TaskDao find error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return i!=0;
    }

    public List<Task> findByStudent(int id) throws DaoException {
        return findBy(id,DataBaseInjection.FIND_ALL_TASKS_BY_STUDENT);
    }

    public List<Task> findBySubject(int id) throws DaoException {
        return findBy(id,DataBaseInjection.FIND_ALL_TASKS_BY_SUBJECT);
    }

    public List<Task> findBySubjectAndStudent(int idStudent, int idSubject) throws DaoException {
        List<Task> tasks = new ArrayList<>();
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.FIND_EVENTS_BY_STUDENT_AND_TEACHER);
                statement.setInt(1,idStudent);
                statement.setInt(2,idSubject);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int mark = result.getInt(1);
                    int id = result.getInt(2);
                    String text = result.getString(3);
                    int subject_id = result.getInt(4);
                    int student_id = result.getInt(5);
                    String surname = result.getString(6);
                    String first_name = result.getString(7);
                    int group = result.getInt(8);
                    int grant = result.getInt(9);
                    int average_mark = result.getInt(10);
                    String student_password = result.getString(11);
                    String student_login = result.getString(12);
                    String answer = result.getString(13);
                    tasks.add(new Task(new Student(first_name,surname,student_password,student_login,group,average_mark,grant,student_id),mark,text,answer,subject_id,id));
                }
                close(statement);
            } catch (SQLException e) {
                throw new DaoException("Statement TaskDao find by Student and Subject  error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return tasks;
    }

    private List<Task> findBy(int value, String query) throws DaoException{
        List<Task> tasks = new ArrayList<>();
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1,value);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int mark = result.getInt(1);
                    int id = result.getInt(2);
                    String text = result.getString(3);
                    int subject_id = result.getInt(4);
                    int student_id = result.getInt(5);
                    String surname = result.getString(6);
                    String first_name = result.getString(7);
                    int group = result.getInt(8);
                    int grant = result.getInt(9);
                    int average_mark = result.getInt(10);
                    String student_password = result.getString(11);
                    String student_login = result.getString(12);
                    String answer = result.getString(13);
                    tasks.add(new Task(new Student(first_name,surname,student_password,student_login,group,average_mark,grant,student_id),mark,text,answer,subject_id,id));
                }
                close(statement);
            } catch (SQLException e) {
                throw new DaoException("Statement TaskDao find error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return tasks;
    }

    @Override
    public List<Task> findAll() throws DaoException {
        List<Task> tasks = new ArrayList<>();
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.FIND_ALL_TASKS);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int mark = result.getInt(1);
                    int id = result.getInt(2);
                    String text = result.getString(3);
                    int subject_id = result.getInt(4);
                    int student_id = result.getInt(5);
                    String surname = result.getString(6);
                    String first_name = result.getString(7);
                    int group = result.getInt(8);
                    int grant = result.getInt(9);
                    int average_mark = result.getInt(10);
                    String student_password = result.getString(11);
                    String student_login = result.getString(12);
                    String answer = result.getString(13);
                    tasks.add(new Task(new Student(first_name,surname,student_password,student_login,group,average_mark,grant,student_id),mark,text,answer,subject_id,id));
                }
                close(statement);
            } catch (SQLException e) {
                throw new DaoException("Statement TaskDao creation error", e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return tasks;
    }
//(subject_task_id, task_text, mark, student_fk,student_answer,subject_fk)
    @Override
    public boolean add(Task entity) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        int i = 0;
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(DataBaseInjection.ADD_TASK);
                statement.setString(1, entity.getText());
                statement.setInt(2,entity.getMark());
                statement.setInt(3,entity.getStudent().getId());
                statement.setString(4, entity.getAnswer());
                statement.setInt(5,entity.getIdSubject());
                i = statement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoException("Statement TaskDao add creation error" + e, e);
            } finally {
                ConnectionPool.INSTANCE.releaseConnection(connection);
            }
        }
        return i != 0;
    }
}
