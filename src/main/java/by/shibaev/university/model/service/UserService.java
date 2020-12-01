package by.shibaev.university.model.service;

import by.shibaev.university.controller.command.util.RequestData;
import by.shibaev.university.model.entity.Entity;
import by.shibaev.university.model.entity.Task;
import by.shibaev.university.model.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;
import java.util.Optional;

public interface UserService<T extends Entity> {
    Logger logger = LogManager.getLogger();

    List<Task> selectEvents(int id) throws ServiceException;
    List<Task> selectTasks(int id) throws ServiceException;
    Optional<T> login(RequestData data) throws ServiceException;
    Optional<T> register(RequestData data) throws ServiceException;
}
