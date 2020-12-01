package by.shibaev.university.model.service.impl;

import by.shibaev.university.controller.command.util.AttributeName;
import by.shibaev.university.controller.command.util.ParameterName;
import by.shibaev.university.controller.command.util.RequestData;
import by.shibaev.university.model.dao.StudentDao;
import by.shibaev.university.model.entity.Student;
import by.shibaev.university.model.entity.Task;
import by.shibaev.university.model.exception.DaoException;
import by.shibaev.university.model.exception.ServiceException;
import by.shibaev.university.model.service.UserService;
import by.shibaev.university.model.validator.InputValidator;
import by.shibaev.university.model.validator.LoginValidator;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Optional;

public class StudentService implements UserService<Student> {
    public static final StudentService INSTANCE  = new StudentService();
    private static final String WRONG_WORD = "Wrong";
    private static final String ALREADY_EXISTS = "User already exists";
    private StudentService(){
    }

    public boolean updateGrant(){
        return false;
    }

    @Override
    public List<Task> selectEvents(int id) throws ServiceException {
        return null;
    }

    @Override
    public List<Task> selectTasks(int id) throws ServiceException {
        return null;
    }

    @Override
    public Optional<Student> login(RequestData data) throws ServiceException {
        Optional<Student> result = Optional.empty();
        String login = data.getParameter(ParameterName.LOGIN).orElseThrow(ServiceException::new)[0];
        String password = data.getParameter(ParameterName.PASSWORD).orElseThrow(ServiceException::new)[0];
        LoginValidator validator = LoginValidator.INSTANCE;
        if(validator.isLoginCorrect(login) && validator.isPasswordCorrect(password)){
            StudentDao studentDao = StudentDao.INSTANCE;
            try {
                Student student;
                Optional<Student> entity = studentDao.login(login,password);
                if(entity.isPresent()){
                    student = entity.get();
                    result = Optional.of(student);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public Optional<Student> register(RequestData data) throws ServiceException {
        Optional<Student> result = Optional.empty();
        StringBuffer wrongMessage = new StringBuffer(WRONG_WORD);
        String login = data.getParameter(ParameterName.LOGIN).orElseThrow(ServiceException::new)[0];
        String password = data.getParameter(ParameterName.PASSWORD).orElseThrow(ServiceException::new)[0];
        String name = data.getParameter(ParameterName.NAME).orElseThrow(ServiceException::new)[0];
        String surname = data.getParameter(ParameterName.SURNAME).orElseThrow(ServiceException::new)[0];
        String group = data.getParameter(ParameterName.GROUP).orElseThrow(ServiceException::new)[0];
        LoginValidator loginValidator = LoginValidator.INSTANCE;
        InputValidator inputValidator = InputValidator.INSTANCE;
        if (!loginValidator.isLoginCorrect(login)){
            wrongMessage.append(" ").append(ParameterName.LOGIN);
        }
        if (!loginValidator.isPasswordCorrect(password)){
            wrongMessage.append(" ").append(ParameterName.PASSWORD);
        }
        if (!inputValidator.isNameValid(name)){
            wrongMessage.append(" ").append(ParameterName.NAME);
        }
        if (!inputValidator.isNameValid(surname)){
            wrongMessage.append(" ").append(ParameterName.SURNAME);
        }
        if (!inputValidator.isNumberValid(group)){
            wrongMessage.append(" ").append(ParameterName.GROUP);
        }
        if(wrongMessage.toString().equals(WRONG_WORD)){
            StudentDao studentDao = StudentDao.INSTANCE;
            try {
                boolean isAdded = studentDao.add(new Student(name,surname,password,login,Integer.parseInt(group)));
                if(isAdded){
                    result = studentDao.login(login,password);
                }
                else {
                    data.setAttribute(AttributeName.WRONG_INPUT, ALREADY_EXISTS);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            data.setAttribute(AttributeName.WRONG_INPUT, wrongMessage);
        }
        return result;
    }
}
