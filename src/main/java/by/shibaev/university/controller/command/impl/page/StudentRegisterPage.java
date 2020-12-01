package by.shibaev.university.controller.command.impl.page;

import by.shibaev.university.controller.command.Command;
import by.shibaev.university.controller.command.util.PagePath;
import by.shibaev.university.controller.command.util.RequestData;

public class StudentRegisterPage implements Command {
    @Override
    public String process(RequestData data) {
        return PagePath.STUDENT_REGISTER;
    }
}
