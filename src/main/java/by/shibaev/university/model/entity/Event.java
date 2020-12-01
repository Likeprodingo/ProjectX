package by.shibaev.university.model.entity;

public class Event extends Entity{
    private String theme;
    private int event_grant;
    private String specification;
    private Teacher teacher;

    public Event(int id, String theme, int event_grant, String specification, Teacher teacher) {
        this.id = id;
        this.theme = theme;
        this.event_grant = event_grant;
        this.specification = specification;
        this.teacher = teacher;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getEvent_grant() {
        return event_grant;
    }

    public void setEvent_grant(int event_grant) {
        this.event_grant = event_grant;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (event_grant != event.event_grant) return false;
        if (theme != null ? !theme.equals(event.theme) : event.theme != null) return false;
        if (specification != null ? !specification.equals(event.specification) : event.specification != null)
            return false;
        return teacher != null ? teacher.equals(event.teacher) : event.teacher == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + event_grant;
        result = 31 * result + (specification != null ? specification.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Event{");
        sb.append("id=").append(id);
        sb.append(", theme='").append(theme).append('\'');
        sb.append(", event_grant=").append(event_grant);
        sb.append(", specification='").append(specification).append('\'');
        sb.append(", teacher=").append(teacher);
        sb.append('}');
        return sb.toString();
    }
}
