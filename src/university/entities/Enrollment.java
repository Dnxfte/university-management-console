package university.entities;

import university.enums.Grade;
import university.interfaces.Payable;

public class Enrollment implements Payable {
    private int id;
    private Student student;
    private Course course;
    private String semester;
    private Grade grade;
    private boolean paid;

    public Enrollment(int id, Student student, Course course, String semester) {
        setId(id);
        setStudent(student);
        setCourse(course);
        setSemester(semester);
        this.grade = Grade.NA;
        this.paid = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID зарахування має бути більше 0.");
        }

        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Студент у зарахуванні не може бути порожнім.");
        }

        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Курс у зарахуванні не може бути порожнім.");
        }

        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        if (semester == null || semester.trim().isEmpty()) {
            throw new IllegalArgumentException("Семестр не може бути порожнім.");
        }

        this.semester = semester.trim();
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Оцінка не може бути порожньою.");
        }

        this.grade = grade;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public void pay() {
        paid = true;
    }

    @Override
    public boolean isPaid() {
        return paid;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", студент: " + student.getFullName()
                + ", курс: " + course.getTitle()
                + ", семестр: " + semester
                + ", оцінка: " + grade
                + ", оплачено: " + (paid ? "так" : "ні");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Enrollment enrollment = (Enrollment) object;
        return id == enrollment.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
