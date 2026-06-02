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

    public Student[] searchStudents(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Пошуковий запит не може бути порожнім.");
        }

        String normalizedQuery = query.trim().toLowerCase();
        int resultCount = 0;

        for (int i = 0; i < count; i++) {
            if (matchesSearch(students[i], normalizedQuery)) {
                resultCount++;
            }
        }

        Student[] result = new Student[resultCount];
        int resultIndex = 0;

        for (int i = 0; i < count; i++) {
            if (matchesSearch(students[i], normalizedQuery)) {
                result[resultIndex] = students[i];
                resultIndex++;
            }
        }

        return result;
    }

    public Student[] filterByStatus(StudentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Статус студента не може бути порожнім.");
        }

        int resultCount = 0;

        for (int i = 0; i < count; i++) {
            if (students[i].getStatus() == status) {
                resultCount++;
            }
        }

        Student[] result = new Student[resultCount];
        int resultIndex = 0;

        for (int i = 0; i < count; i++) {
            if (students[i].getStatus() == status) {
                result[resultIndex] = students[i];
                resultIndex++;
            }
        }

        return result;
    }

    public Student[] filterByStudyYear(int studyYear) {
        if (studyYear < 1 || studyYear > 6) {
            throw new IllegalArgumentException("Рік навчання має бути від 1 до 6.");
        }

        int resultCount = 0;

        for (int i = 0; i < count; i++) {
            if (students[i].getStudyYear() == studyYear) {
                resultCount++;
            }
        }

        Student[] result = new Student[resultCount];
        int resultIndex = 0;

        for (int i = 0; i < count; i++) {
            if (students[i].getStudyYear() == studyYear) {
                result[resultIndex] = students[i];
                resultIndex++;
            }
        }

        return result;
    }

    public Student[] getStudentsSortedByFullName() {
        Student[] result = getAllStudents();

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                String currentName = result[j].getFullName();
                String nextName = result[j + 1].getFullName();

                if (currentName.compareToIgnoreCase(nextName) > 0) {
                    Student temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
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

    private boolean matchesSearch(Student student, String normalizedQuery) {
        String fullName = student.getFullName().toLowerCase();
        String email = student.getEmail().toLowerCase();

        return fullName.contains(normalizedQuery) || email.contains(normalizedQuery);
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
