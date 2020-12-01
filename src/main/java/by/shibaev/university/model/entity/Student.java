package by.shibaev.university.model.entity;

public class Student extends Entity{
    private static final int DEFAULT_ID = 0;
    private String firstName;
    private String surname;
    private String password;
    private String login;
    private int studentGroup;
    private int averageMark;
    private int studentGrant;

    public Student(String firstName, String surname, String password, String login, int studentGroup) {
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
        this.login = login;
        this.studentGroup = studentGroup;
    }

    public Student(String firstName, String surname, String password, String login, int studentGroup, int averageMark, int studentGrant, int id) {
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
        this.login = login;
        this.studentGroup = studentGroup;
        this.averageMark = averageMark;
        this.studentGrant = studentGrant;
        this.id = id;
    }

    public Student(String firstName, String surname, String password, String login, int studentGroup, int averageMark, int studentGrant) {
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
        this.login = login;
        this.studentGroup = studentGroup;
        this.averageMark = averageMark;
        this.studentGrant = studentGrant;
        id = DEFAULT_ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public int getStudentGroup() {
        return studentGroup;
    }

    public int getAverageMark() {
        return averageMark;
    }

    public int getStudentGrant() {
        return studentGrant;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setStudentGroup(int studentGroup) {
        this.studentGroup = studentGroup;
    }

    public void setAverageMark(int averageMark) {
        this.averageMark = averageMark;
    }

    public void setStudentGrant(int studentGrant) {
        this.studentGrant = studentGrant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentGroup != student.studentGroup) return false;
        if (averageMark != student.averageMark) return false;
        if (studentGrant != student.studentGrant) return false;
        if (id != student.id) return false;
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
        if (password != null ? !password.equals(student.password) : student.password != null) return false;
        return login != null ? login.equals(student.login) : student.login == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + studentGroup;
        result = 31 * result + averageMark;
        result = 31 * result + studentGrant;
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Student{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", studentGroup=").append(studentGroup);
        sb.append(", averageMark=").append(averageMark);
        sb.append(", studentGrant=").append(studentGrant);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
