package by.shibaev.university.controller.command.impl;

import by.shibaev.university.controller.command.Command;
import by.shibaev.university.controller.command.util.AttributeName;
import by.shibaev.university.controller.command.util.PagePath;
import by.shibaev.university.controller.command.util.RequestData;
import by.shibaev.university.model.entity.Student;
import by.shibaev.university.model.entity.Task;
import by.shibaev.university.model.entity.Teacher;
import by.shibaev.university.model.exception.ServiceException;
import by.shibaev.university.model.service.impl.StudentService;
import by.shibaev.university.model.service.impl.TeacherService;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Optional;

public class TeacherRegisterCommand implements Command {

    @Override
    public String process(RequestData data) {
        String page = PagePath.TEACHER_REGISTER;
        TeacherService teacherService = TeacherService.INSTANCE;
        try {
            Optional<Teacher> teacher = teacherService.register(data);
            if(teacher.isPresent()){
                data.setSessionAttribute(AttributeName.USER,teacher.get());
                List<Task> tasks = null;
                tasks = teacherService.selectTasks(teacher.get().getSubject().getId());
                data.setAttribute(AttributeName.TASKS,tasks);
                page = PagePath.TEACHER_TASK;
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR,e);
            try {
                data.setAttribute(AttributeName.SUBJECTS,teacherService.selectSubjects());
            } catch (ServiceException error) {
                logger.log(Level.ERROR,error);
            }
        }
        data.restore();
        return page;
    }
}
