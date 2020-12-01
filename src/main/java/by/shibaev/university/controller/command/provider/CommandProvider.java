package by.shibaev.university.controller.command.provider;

import by.shibaev.university.controller.command.Command;
import by.shibaev.university.controller.command.util.RequestData;
import by.shibaev.university.controller.command.type.CommandType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();
    private static String COMMAND = "command";

    public static Optional<Command> provide(RequestData data) {
        String[] name;
        Optional<Command> commandProcessor = Optional.empty();
        Optional<String[]> commandName = data.getParameter(COMMAND);
        if (commandName.isPresent() && commandName.get().length == 1) {
            name = commandName.get();
            for (CommandType commandType : CommandType.values()) {
                if (commandType.getName().equals(name[0])) {
                    commandProcessor = Optional.of(commandType.getCommand());
                    logger.log(Level.INFO, "Command tag: {}", commandType.getName());
                    break;
                }
            }
        } else {
            logger.log(Level.WARN, "Wrong command data");
        }
        return commandProcessor;
    }
}
