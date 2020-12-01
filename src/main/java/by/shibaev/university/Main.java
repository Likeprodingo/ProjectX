package by.shibaev.university;

import by.shibaev.university.model.dao.StudentDao;
import by.shibaev.university.model.dao.SubjectDao;
import by.shibaev.university.model.entity.Student;
import by.shibaev.university.model.entity.Subject;
import by.shibaev.university.model.exception.DaoException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, DaoException {
        SubjectDao subjectDao = SubjectDao.INSTANCE;
        System.out.println(subjectDao.findAll());

    }
}
