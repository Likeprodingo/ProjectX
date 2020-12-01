package by.shibaev.university.model.entity;

public class Subject extends Entity{

    private String title;
    private int hours;

    public Subject(int id, String title, int hours) {
        this.id = id;
        this.title = title;
        this.hours = hours;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (id != subject.id) return false;
        if (hours != subject.hours) return false;
        return title != null ? title.equals(subject.title) : subject.title == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + hours;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Subject{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", hours=").append(hours);
        sb.append('}');
        return sb.toString();
    }
}
