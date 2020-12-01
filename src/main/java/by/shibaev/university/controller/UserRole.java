package by.shibaev.university.controller;

import by.shibaev.university.controller.command.type.CommandType;

import java.util.EnumSet;
import java.util.Set;

import static by.shibaev.university.controller.command.type.CommandType.*;

public enum UserRole {
    ADMIN(EnumSet.of(
            EMPTY_COMMAND
    )),
    STUDENT(EnumSet.of(
            EMPTY_COMMAND
    )),
    TEACHER(EnumSet.of(
            EMPTY_COMMAND
    ));

    private final Set<CommandType> availableCommands;

    UserRole(Set<CommandType> availableCommands) {
        this.availableCommands = availableCommands;
    }

    public Set<CommandType> getAvailableCommands() {
        return availableCommands;
    }
}
