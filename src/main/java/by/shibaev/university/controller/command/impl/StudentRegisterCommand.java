package by.shibaev.university.controller.command.impl;

import by.shibaev.university.controller.command.Command;
import by.shibaev.university.controller.command.util.AttributeName;
import by.shibaev.university.controller.command.util.PagePath;
import by.shibaev.university.controller.command.util.RequestData;
import by.shibaev.university.model.entity.Student;
import by.shibaev.university.model.exception.ServiceException;
import by.shibaev.university.model.service.impl.StudentService;
import org.apache.logging.log4j.Level;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentRegisterCommand implements Command {

    @Override
    public String process(RequestData data) {
        String page = PagePath.STUDENT_REGISTER;
        StudentService studentService = StudentService.INSTANCE;
        try {
            Optional<Student> student = studentService.register(data);
            if(student.isPresent()){
                data.setSessionAttribute(AttributeName.USER,student.get());
                page = PagePath.STUDENT_TASK;
            }
        } catch (ServiceException e) {
            data.setAttribute(AttributeName.WRONG_INPUT,e);
            logger.log(Level.ERROR,e);
        }
        data.restore();
        return page;
    }
}
