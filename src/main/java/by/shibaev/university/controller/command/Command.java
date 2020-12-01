package by.shibaev.university.controller.command;

import by.shibaev.university.controller.command.util.RequestData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Command {
    Logger logger = LogManager.getLogger();
    String process(RequestData data);
}
