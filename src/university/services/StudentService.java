package university.services;

import university.entities.Student;
import university.enums.StudentStatus;

public class StudentService {
    private Student[] students;
    private int count;
    private int nextId;

    public StudentService() {
        students = new Student[100];
        count = 0;
        nextId = 1;
    }

    public Student addStudent(String fullName, String email, int studyYear, StudentStatus status) {
        if (count == students.length) {
            throw new IllegalArgumentException("Немає місця для нового студента.");
        }

        Student student = new Student(nextId, fullName, email, studyYear, status);
        students[count] = student;
        count++;
        nextId++;

        return student;
    }

    public Student[] getAllStudents() {
        Student[] result = new Student[count];

        for (int i = 0; i < count; i++) {
            result[i] = students[i];
        }

        return result;
    }

    public Student findStudentById(int id) {
        int index = findStudentIndexById(id);

        if (index == -1) {
            return null;
        }

        return students[index];
    }

    public void updateStudent(int id, String fullName, String email, int studyYear) {
        Student student = findStudentById(id);

        if (student == null) {
            throw new IllegalArgumentException("Студента з таким ID не знайдено.");
        }

        student.setFullName(fullName);
        student.setEmail(email);
        student.setStudyYear(studyYear);
    }

    public void changeStudentStatus(int id, StudentStatus status) {
        Student student = findStudentById(id);

        if (student == null) {
            throw new IllegalArgumentException("Студента з таким ID не знайдено.");
        }

        student.setStatus(status);
    }

    public void deleteStudent(int id) {
        int index = findStudentIndexById(id);

        if (index == -1) {
            throw new IllegalArgumentException("Студента з таким ID не знайдено.");
        }

        for (int i = index; i < count - 1; i++) {
            students[i] = students[i + 1];
        }

        students[count - 1] = null;
        count--;
    }

    public int getCount() {
        return count;
    }

    private int findStudentIndexById(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) {
                return i;
            }
        }

        return -1;
    }
}
