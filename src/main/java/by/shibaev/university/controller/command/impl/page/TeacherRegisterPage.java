package by.shibaev.university.controller.command.impl.page;

import by.shibaev.university.controller.command.Command;
import by.shibaev.university.controller.command.util.AttributeName;
import by.shibaev.university.controller.command.util.PagePath;
import by.shibaev.university.controller.command.util.RequestData;
import by.shibaev.university.model.entity.Subject;
import by.shibaev.university.model.exception.ServiceException;
import by.shibaev.university.model.service.impl.TeacherService;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TeacherRegisterPage implements Command {
    private static final String LOCALE_PATH = "locale/pagecontent";
    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    @Override
    public String process(RequestData data) {
        String localeStr = (String) data.getSessionAttribute(AttributeName.LOCALE);
        Locale locale = Locale.forLanguageTag(localeStr.replace(UNDERSCORE, HYPHEN));
        ResourceBundle localeBundle = ResourceBundle.getBundle(LOCALE_PATH, locale);
        TeacherService teacherService = TeacherService.INSTANCE;
        try {
            List<Subject> subjects = teacherService.selectSubjects();
            data.setAttribute(AttributeName.SUBJECTS,subjects);
        } catch (ServiceException e) {
            data.setAttribute(AttributeName.WRONG_INPUT, localeBundle.getString("message.login.somethingWrong"));
            logger.log(Level.ERROR,e);
        }
        data.restore();
        return PagePath.TEACHER_REGISTER;
    }
}
