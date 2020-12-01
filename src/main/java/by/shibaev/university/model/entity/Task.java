package by.shibaev.university.model.entity;

public class Task extends Entity{
    private Student student;
    private int mark;
    private String text;
    private String answer;
    private int idSubject;

    public Task(Student student, int mark, String text, String answer, int idSubject, int id) {
        this.student = student;
        this.mark = mark;
        this.text = text;
        this.answer = answer;
        this.idSubject = idSubject;
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (mark != task.mark) return false;
        if (idSubject != task.idSubject) return false;
        if (id != task.id) return false;
        if (student != null ? !student.equals(task.student) : task.student != null) return false;
        if (text != null ? !text.equals(task.text) : task.text != null) return false;
        return answer != null ? answer.equals(task.answer) : task.answer == null;
    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;
        result = 31 * result + mark;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + idSubject;
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Task{");
        sb.append("student=").append(student);
        sb.append(", mark=").append(mark);
        sb.append(", text='").append(text).append('\'');
        sb.append(", answer='").append(answer).append('\'');
        sb.append(", idSubject=").append(idSubject);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
