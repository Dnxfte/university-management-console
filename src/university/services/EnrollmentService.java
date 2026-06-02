package university.services;

import university.entities.Course;
import university.entities.Enrollment;
import university.entities.Student;
import university.enums.Grade;
import university.util.GPAUtils;

public class EnrollmentService {
    private Enrollment[] enrollments;
    private int count;
    private int nextId;

    public EnrollmentService() {
        enrollments = new Enrollment[200];
        count = 0;
        nextId = 1;
    }

    public Enrollment addEnrollment(Student student, Course course, String semester) {
        if (count == enrollments.length) {
            throw new IllegalArgumentException("Немає місця для нового зарахування.");
        }

        if (hasEnrollment(student, course, semester)) {
            throw new IllegalArgumentException("Студент уже зарахований на цей курс у цьому семестрі.");
        }

        Enrollment enrollment = new Enrollment(nextId, student, course, semester);
        enrollments[count] = enrollment;
        count++;
        nextId++;

        return enrollment;
    }

    public Enrollment[] getAllEnrollments() {
        Enrollment[] result = new Enrollment[count];

        for (int i = 0; i < count; i++) {
            result[i] = enrollments[i];
        }

        return result;
    }

    public Enrollment findEnrollmentById(int id) {
        int index = findEnrollmentIndexById(id);

        if (index == -1) {
            return null;
        }

        return enrollments[index];
    }

    public void setGrade(int id, Grade grade) {
        Enrollment enrollment = findEnrollmentById(id);

        if (enrollment == null) {
            throw new IllegalArgumentException("Зарахування з таким ID не знайдено.");
        }

        enrollment.setGrade(grade);
    }

    public void markAsPaid(int id) {
        Enrollment enrollment = findEnrollmentById(id);

        if (enrollment == null) {
            throw new IllegalArgumentException("Зарахування з таким ID не знайдено.");
        }

        enrollment.pay();
    }

    public Enrollment[] getEnrollmentsByStudent(Student student) {
        int resultCount = 0;

        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStudent().getId() == student.getId()) {
                resultCount++;
            }
        }

        Enrollment[] result = new Enrollment[resultCount];
        int resultIndex = 0;

        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStudent().getId() == student.getId()) {
                result[resultIndex] = enrollments[i];
                resultIndex++;
            }
        }

        return result;
    }

    public double calculateGPAForStudent(Student student) {
        return GPAUtils.calculateGPA(getEnrollmentsByStudent(student));
    }

    public int getCount() {
        return count;
    }

    private boolean hasEnrollment(Student student, Course course, String semester) {
        for (int i = 0; i < count; i++) {
            boolean sameStudent = enrollments[i].getStudent().getId() == student.getId();
            boolean sameCourse = enrollments[i].getCourse().getId() == course.getId();
            boolean sameSemester = enrollments[i].getSemester().equalsIgnoreCase(semester.trim());

            if (sameStudent && sameCourse && sameSemester) {
                return true;
            }
        }

        return false;
    }

    private int findEnrollmentIndexById(int id) {
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getId() == id) {
                return i;
            }
        }

        return -1;
    }
}
