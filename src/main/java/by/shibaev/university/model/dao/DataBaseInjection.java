package by.shibaev.university.model.dao;

public class DataBaseInjection {

    //Student
    public static final String FIND_ALL_STUDENTS = "SELECT student_id,student_login, student_password, first_name, surname, student_group, student_grant, average_mark FROM student";
    public static final String ADD_STUDENT = "INSERT INTO student(first_name, surname, student_group,student_login, student_password) VALUES (?, ? , ?, ?, ?);";
    public static final String STUDENT_LOGIN = "SELECT student_id, first_name, surname, student_group, student_grant, average_mark FROM student WHERE student_login = ? AND student_password = ?";
    public static final String SUBSCRIBE_EVENT = "INSERT INTO student_event(student_fk, event_fk) VALUES (?,?)";
    public static final String SUBSCRIBE_SUBJECT = "INSERT INTO student_task(student_fk, subject_fk) VALUES (?,?)";
    public static final String UPLOAD_TASK = "UPDATE subject_task SET student_answer = ? WHERE student_fk = ? AND subject_fk = ?";
    public static final String UPDATE_AVERAGE_MARK = "UPDATE student SET average_mark = ? WHERE student_id = ?";
    public static final String UPDATE_STUDENT_GRANT = "UPDATE student SET student_grant = ? WHERE student_id = ?";
    public static final String FIND_STUDENT_BY_ID = "SELECT first_name, surname, student_group, student_grant, average_mark FROM student WHERE student_id = ?";
    public static final String FIND_STUDENT_BY_SUBJECT = "SELECT s.student_id, s.first_name, s.surname, s.student_group, s.student_grant, s.average_mark FROM student s INNER JOIN subject_task st ON s.student_id = st.student_fk WHERE st.subject_fk = ?";

    //Teacher
    public static final String TEACHER_LOGIN = "SELECT t.teacher_id, t.teacher_login, t.teacher_password, t.first_name, t.surname, s.subject_id, s.title,s.hours FROM teacher t INNER JOIN subject s ON s.subject_id = t.subject_fk WHERE t.teacher_login = ? AND t.teacher_password = ?";
    public static final String FIND_ALL_TEACHER = "SELECT t.teacher_id,t.teacher_login, t.teacher_password, t.first_name, t.surname,t.subject_fk, s.subject_id, title,hours FROM teacher t INNER JOIN subject s ON s.subject_id = t.subject_fk";
    public static final String ADD_TEACHER = "INSERT INTO teacher(first_name,teacher_login,teacher_password, surname, subject_fk) VALUES (? , ?, ?, ? , ?);";
    public static final String UPDATE_STUDENT_TASK = "UPDATE student_task SET task_text = ?, mark = ? WHERE student_fk = ?";
    public static final String SET_STUDENT_MARK = "UPDATE student_task SET mark = ? WHERE student_fk = ? AND subject_fk = ?";
    public static final String SET_EVENT_TASK = "UPDATE student_event SET text = ? WHERE student_fk = ? AND event_fk = ?";

    //Subject
    public static final String FIND_ALL_SUBJECT = "SELECT subject_id, title, hours FROM subject";
    public static final String ADD_SUBJECT = "INSERT INTO subject(title, hours) VALUES (?,?);";

    //Event
    public static final String FIND_ALL_EVENTS = "SELECT e.id,e.theme,t.first_name,t.surname,t.teacher_login, t.teacher_password , e.money,e.specification,  s.subject_id, s.title,s.hours, t.teacher_id FROM event e INNER JOIN teacher t ON t.teacher_id = e.teacher_fk INNER JOIN subject s ON s.subject_id = t.subject_fk ";
    public static final String ADD_EVENT = "INSERT INTO event(theme,teacher_fk,money,specification) VALUES (?,?,?,?)";
    public static final String FIND_EVENTS_BY_TEACHER = "SELECT e.id,e.theme,t.first_name,t.surname,t.teacher_login, t.teacher_password , e.money,e.specification,  s.subject_id, s.title,s.hours, t.teacher_id FROM event e INNER JOIN teacher t ON t.teacher_id = e.teacher_fk INNER JOIN subject s ON s.subject_id = t.subject_fk  WHERE teacher_fk = ?";
    public static final String FIND_EVENTS_BY_STUDENT = "SELECT e.id,e.theme,t.first_name,t.surname,t.teacher_login, t.teacher_password , e.money,e.specification,  s.subject_id, s.title,s.hours, t.teacher_id FROM event e INNER JOIN teacher t ON t.teacher_id = e.teacher_fk INNER JOIN subject s ON s.subject_id = t.subject_fk INNER JOIN student_event se ON se.event_fk = e.event_id WHERE se.student_fk = ?";
    public static final String FIND_EVENTS_BY_STUDENT_AND_TEACHER = "SELECT e.id,e.theme,t.first_name,t.surname,t.teacher_login, t.teacher_password , e.money,e.specification,  s.subject_id, s.title,s.hours, t.teacher_id FROM event e INNER JOIN teacher t ON t.teacher_id = e.teacher_fk INNER JOIN subject s ON s.subject_id = t.subject_fk INNER JOIN student_event se ON se.event_fk = e.event_id WHERE se.student_fk = ? AND e.teacher_fk = ?";


    //Tasks
    public static final String FIND_ALL_TASKS_BY_STUDENT_AND_SUBJECT = "SELECT t.mark, t.subject_task_id, t.task_text, t.subject_fk, s.student_id, s.surname, s.first_name, s.student_group, s.student_grant, s.average_mark,s.student_password,s.student_login FROM subject_task t INNER JOIN student s ON s.student_id = t.student_fk WHERE s.student_id = ? AND s.subject_fk;";
    public static final String ADD_TASK = "INSERT INTO subject_task(task_text, mark, student_fk,student_answer,subject_fk) VALUES (?,?,?,?,?)";
    public static final String FIND_ALL_TASKS_BY_STUDENT = "SELECT t.mark, t.subject_task_id, t.task_text, t.subject_fk, s.student_id, s.surname, s.first_name, s.student_group, s.student_grant, s.average_mark,s.student_password,s.student_login FROM subject_task t INNER JOIN student s ON s.student_id = t.student_fk WHERE s.student_id = ?;";
    public static final String FIND_ALL_TASKS_BY_SUBJECT = "SELECT t.mark, t.subject_task_id, t.task_text, t.subject_fk, s.student_id, s.surname, s.first_name, s.student_group, s.student_grant, s.average_mark,s.student_password,s.student_login FROM subject_task t INNER JOIN student s ON s.student_id = t.student_fk WHERE t.subject_fk = ?;";
    public static final String FIND_ALL_TASKS = "SELECT t.mark, t.subject_task_id, t.task_text, t.subject_fk, s.student_id, s.surname, s.first_name, s.student_group, s.student_grant, s.average_mark,s.student_password,s.student_login,t.answer FROM subject_task t INNER JOIN student s ON s.student_id = t.student_fk;";
    private DataBaseInjection() {
    }
}
