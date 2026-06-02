package university.entities;

import university.enums.StudentStatus;

public class Student extends Person {
    private int studyYear;
    private StudentStatus status;

    public Student(int id, String fullName, String email, int studyYear, StudentStatus status) {
        super(id, fullName, email);
        setStudyYear(studyYear);
        setStatus(status);
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        if (studyYear < 1 || studyYear > 6) {
            throw new IllegalArgumentException("Рік навчання має бути від 1 до 6.");
        }

        this.studyYear = studyYear;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Статус студента не може бути порожнім.");
        }

        this.status = status;
    }

    @Override
    public String toString() {
        return getPersonInfo() + ", рік навчання: " + studyYear + ", статус: " + status;
    }
}
