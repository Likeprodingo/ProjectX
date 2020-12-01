package by.shibaev.university.controller.command.impl.page;

import by.shibaev.university.controller.command.Command;
import by.shibaev.university.controller.command.util.AttributeName;
import by.shibaev.university.controller.command.util.PagePath;
import by.shibaev.university.controller.command.util.RequestData;

public class LoginPage implements Command {

    private final static String DEFAULT_LOCALE = "ru_RU";

    @Override
    public String process(RequestData data) {
        if (data.isSessionNew()){
            data.setSessionAttribute(AttributeName.LOCALE,DEFAULT_LOCALE);
        }
        data.restore();
        return PagePath.LOGIN;
    }
}
