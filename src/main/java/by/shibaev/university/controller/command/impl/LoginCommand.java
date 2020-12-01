package by.shibaev.university.controller.command.impl;

import by.shibaev.university.controller.command.Command;
import by.shibaev.university.controller.command.util.AttributeName;
import by.shibaev.university.controller.command.util.PagePath;
import by.shibaev.university.controller.command.util.ParameterName;
import by.shibaev.university.controller.command.util.RequestData;
import by.shibaev.university.model.entity.Entity;
import by.shibaev.university.model.entity.Task;
import by.shibaev.university.model.exception.ServiceException;
import by.shibaev.university.model.service.UserService;
import by.shibaev.university.model.service.impl.StudentService;
import by.shibaev.university.model.service.impl.TeacherService;
import org.apache.logging.log4j.Level;

import javax.management.Attribute;
import java.util.*;

public class LoginCommand implements Command {

    private static final String STUDENT_ROLE = "student";
    private static final String TEACHER_ROLE = "teacher";
    private static final String LOCALE_PATH = "locale/pagecontent";
    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    @Override
    public String process(RequestData data) {
        String page;

        String localeStr = (String) data.getSessionAttribute(AttributeName.LOCALE);
        Locale locale = Locale.forLanguageTag(localeStr.replace(UNDERSCORE, HYPHEN));
        ResourceBundle localeBundle = ResourceBundle.getBundle(LOCALE_PATH, locale);

        Optional<String[]> roles = data.getParameter(ParameterName.ROLE);
        if (roles.isPresent() && roles.get().length == 1) {
            String role = roles.get()[0];
            UserService service;
            if (role.equals(STUDENT_ROLE)) {
                service = StudentService.INSTANCE;
            } else {
                service = TeacherService.INSTANCE;
            }
            try {
                Optional e = service.login(data);
                if (e.isPresent()) {
                    Entity entity = (Entity) e.get();
                    page = role.equals(STUDENT_ROLE) ? PagePath.STUDENT_TASK : PagePath.TEACHER_TASK;
                    List<Task> tasks = service.selectTasks(entity.getId());
                    data.setAttribute(AttributeName.TASKS,tasks);
                    data.setSessionAttribute(AttributeName.USER, entity);
                }
                else {
                    data.setAttribute(AttributeName.WRONG_INPUT,localeBundle.getString("message.login.wrongInput"));
                    page = PagePath.LOGIN;
                }
            }catch (ServiceException e){
                logger.log(Level.ERROR,"Wrong Input ", e);
                page = PagePath.LOGIN;
                data.setAttribute(AttributeName.WRONG_INPUT,localeBundle.getString("message.login.chooseRole"));
            }
        } else {
            page = PagePath.LOGIN;
            data.setAttribute(AttributeName.WRONG_INPUT,localeBundle.getString("message.login.chooseRole"));
        }
        data.restore();
        return page;
    }
}
