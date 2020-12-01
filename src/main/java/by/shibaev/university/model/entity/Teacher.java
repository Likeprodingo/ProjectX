package by.shibaev.university.model.entity;

public class Teacher extends Entity{
    private static final int DEFAULT_ID = 0;
    private String firstName;
    private String surname;
    private Subject subject;
    private String login;
    private String password;

    public Teacher(int id, String firstName, String surname, Subject subject, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.subject = subject;
        this.login = login;
        this.password = password;
    }

    public Teacher(String firstName, String surname, Subject subject, String login, String password) {
        this.id = DEFAULT_ID;
        this.firstName = firstName;
        this.surname = surname;
        this.subject = subject;
        this.login = login;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (id != teacher.id) return false;
        if (firstName != null ? !firstName.equals(teacher.firstName) : teacher.firstName != null) return false;
        if (surname != null ? !surname.equals(teacher.surname) : teacher.surname != null) return false;
        if (subject != null ? !subject.equals(teacher.subject) : teacher.subject != null) return false;
        if (login != null ? !login.equals(teacher.login) : teacher.login != null) return false;
        return password != null ? password.equals(teacher.password) : teacher.password == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Teacher{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", subject=").append(subject);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
