package by.shibaev.university.controller.command.type;

import by.shibaev.university.controller.command.Command;
import by.shibaev.university.controller.command.impl.LoginCommand;
import by.shibaev.university.controller.command.impl.SaveTaskCommand;
import by.shibaev.university.controller.command.impl.StudentRegisterCommand;
import by.shibaev.university.controller.command.impl.TeacherRegisterCommand;
import by.shibaev.university.controller.command.impl.page.LoginPage;
import by.shibaev.university.controller.command.impl.page.StudentRegisterPage;
import by.shibaev.university.controller.command.impl.page.TeacherRegisterPage;

public enum CommandType {

    LOGIN_PAGE("login_page",new LoginPage()),
    REGISTER_STUDENT_PAGE("teacher_registration_page",new TeacherRegisterPage()),
    REGISTER_TEACHER_PAGE("student_registration_page",new StudentRegisterPage()),

    SAVE_TASK("save_task", new SaveTaskCommand()),
    REGISTER_STUDENT("teacher_registration",new TeacherRegisterCommand()),
    REGISTER_TEACHER("student_registration",new StudentRegisterCommand()),
    LOGIN("login",new LoginCommand()),
    EMPTY_COMMAND("",null),
    GET_MARKS_BY_COUNTRY("marks_country", null);


    private String name;
    private Command command;

    public String getName() {
        return name;
    }

    public Command getCommand() {
        return command;
    }

    CommandType(String name, Command command) {
        this.name = name;
        this.command = command;
    }
}
