package university.entities;

public class Course {
    private int id;
    private String title;
    private int credits;
    private Teacher teacher;

    public Course(int id, String title, int credits, Teacher teacher) {
        setId(id);
        setTitle(title);
        setCredits(credits);
        setTeacher(teacher);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID курсу має бути більше 0.");
        }

        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва курсу не може бути порожньою.");
        }

        this.title = title.trim();
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits <= 0) {
            throw new IllegalArgumentException("Кількість кредитів має бути більше 0.");
        }

        this.credits = credits;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Викладач курсу не може бути порожнім.");
        }

        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", назва: " + title
                + ", кредити: " + credits
                + ", викладач: " + teacher.getFullName();
    }
}
