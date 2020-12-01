package by.shibaev.university.model.service.impl;

import by.shibaev.university.controller.command.util.AttributeName;
import by.shibaev.university.controller.command.util.ParameterName;
import by.shibaev.university.controller.command.util.RequestData;
import by.shibaev.university.model.dao.StudentDao;
import by.shibaev.university.model.dao.SubjectDao;
import by.shibaev.university.model.dao.TaskDao;
import by.shibaev.university.model.dao.TeacherDao;
import by.shibaev.university.model.entity.Student;
import by.shibaev.university.model.entity.Subject;
import by.shibaev.university.model.entity.Task;
import by.shibaev.university.model.entity.Teacher;
import by.shibaev.university.model.exception.DaoException;
import by.shibaev.university.model.exception.ServiceException;
import by.shibaev.university.model.service.UserService;
import by.shibaev.university.model.validator.InputValidator;
import by.shibaev.university.model.validator.LoginValidator;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Optional;

public class TeacherService implements UserService<Teacher> {
    public static final TeacherService INSTANCE = new TeacherService();
    private static final String WRONG_WORD = "Wrong";
    private static final String ALREADY_EXISTS = "User already exists";
    private static final int MAX_TEXT_LENGTH = 300;

    private TeacherService() {
    }

    @Override
    public List<Task> selectEvents(int id) throws ServiceException {
        return null;
    }

    public boolean updateTask(RequestData data) throws ServiceException{
        boolean result = false;
        TaskDao taskDao = TaskDao.INSTANCE;
        String idVal = data.getParameter(ParameterName.ID).orElseThrow(ServiceException::new)[0];
        String text = data.getParameter(ParameterName.TEXT).orElseThrow(ServiceException::new)[0];
        String markVal = data.getParameter(ParameterName.MARK).orElseThrow(ServiceException::new)[0];
        InputValidator inputValidator = InputValidator.INSTANCE;
        if (inputValidator.isNumberValid(idVal) && inputValidator.isNumberValid(markVal) && text.length() < MAX_TEXT_LENGTH) {
            try {
                int id = Integer.parseInt(idVal);
                int mark = Integer.parseInt(markVal);
                result = taskDao.updateTask(id,text,mark);
                if(result){
                    logger.log(Level.INFO,"Task update complete");
                    StudentService studentService = StudentService.INSTANCE;
                    studentService.updateGrant();
                }
                else
                {
                    logger.log(Level.WARN,"Task hasn't updated");
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public List<Task> selectTasks(int id) throws ServiceException{
        TaskDao dao = TaskDao.INSTANCE;
        List<Task> tasks = null;
        try {
            tasks = dao.findBySubject(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tasks;
    }

    public List<Subject> selectSubjects() throws ServiceException {
        SubjectDao subjectDao = SubjectDao.INSTANCE;
        List<Subject> subjects = null;
        try {
            subjects = subjectDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return subjects;
    }

    @Override
    public Optional<Teacher> login(RequestData data) throws ServiceException {
        Optional<Teacher> result = Optional.empty();
        String login = data.getParameter(ParameterName.LOGIN).orElseThrow(ServiceException::new)[0];
        String password = data.getParameter(ParameterName.PASSWORD).orElseThrow(ServiceException::new)[0];
        LoginValidator validator = LoginValidator.INSTANCE;
        if (validator.isLoginCorrect(login) && validator.isPasswordCorrect(password)) {
            TeacherDao teacherDao = TeacherDao.INSTANCE;
            try {
                Teacher teacher;
                Optional<Teacher> entity = teacherDao.login(login, password);
                if (entity.isPresent()) {
                    teacher = entity.get();
                    result = Optional.of(teacher);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public Optional<Teacher> register(RequestData data) throws ServiceException {
        Optional<Teacher> result = Optional.empty();
        StringBuffer wrongMessage = new StringBuffer(WRONG_WORD);
        String login = data.getParameter(ParameterName.LOGIN).orElseThrow(ServiceException::new)[0];
        String password = data.getParameter(ParameterName.PASSWORD).orElseThrow(ServiceException::new)[0];
        String name = data.getParameter(ParameterName.NAME).orElseThrow(ServiceException::new)[0];
        String surname = data.getParameter(ParameterName.SURNAME).orElseThrow(ServiceException::new)[0];
        String subjectId = data.getParameter(ParameterName.SUBJECT).orElseThrow(ServiceException::new)[0];
        logger.log(Level.INFO, "Subject {}", subjectId);
        LoginValidator loginValidator = LoginValidator.INSTANCE;
        InputValidator inputValidator = InputValidator.INSTANCE;
        if (!loginValidator.isLoginCorrect(login)) {
            wrongMessage.append(" ").append(ParameterName.LOGIN);
        }
        if (!loginValidator.isPasswordCorrect(password)) {
            wrongMessage.append(" ").append(ParameterName.PASSWORD);
        }
        if (!inputValidator.isNameValid(name)) {
            wrongMessage.append(" ").append(ParameterName.NAME);
        }
        if (!inputValidator.isNameValid(surname)) {
            wrongMessage.append(" ").append(ParameterName.SURNAME);
        }
        if (!inputValidator.isNumberValid(subjectId)) {
            wrongMessage.append(" ").append(ParameterName.SUBJECT);
        }
        if (wrongMessage.toString().equals(WRONG_WORD)) {
            TeacherDao teacherDao = TeacherDao.INSTANCE;
            try {
                SubjectDao subjectDao = SubjectDao.INSTANCE;
                List<Subject> subjects = subjectDao.findAll();
                int id = Integer.parseInt(subjectId);
                Subject subject = null;
                for (Subject s : subjects) {
                    if (s.getId() == id) {
                        subject = s;
                    }
                }
                teacherDao.add(new Teacher(name, surname, subject, login, password));
                result = teacherDao.login(login, password);
            } catch (DaoException e) {
                data.setAttribute(AttributeName.WRONG_INPUT, ALREADY_EXISTS);
                throw new ServiceException(e);
            }
        } else {
            data.setAttribute(AttributeName.WRONG_INPUT, wrongMessage);
        }
        return result;
    }
}

