package university.services;

import university.entities.Teacher;
import university.enums.TeacherPosition;

public class TeacherService {
    private Teacher[] teachers;
    private int count;
    private int nextId;

    public TeacherService() {
        teachers = new Teacher[100];
        count = 0;
        nextId = 1;
    }

    public Teacher addTeacher(String fullName, String email, TeacherPosition position) {
        if (count == teachers.length) {
            throw new IllegalArgumentException("Немає місця для нового викладача.");
        }

        Teacher teacher = new Teacher(nextId, fullName, email, position);
        teachers[count] = teacher;
        count++;
        nextId++;

        return teacher;
    }

    public Teacher[] getAllTeachers() {
        Teacher[] result = new Teacher[count];

        for (int i = 0; i < count; i++) {
            result[i] = teachers[i];
        }

        return result;
    }

    public Teacher findTeacherById(int id) {
        int index = findTeacherIndexById(id);

        if (index == -1) {
            return null;
        }

        return teachers[index];
    }

    public void updateTeacher(int id, String fullName, String email, TeacherPosition position) {
        Teacher teacher = findTeacherById(id);

        if (teacher == null) {
            throw new IllegalArgumentException("Викладача з таким ID не знайдено.");
        }

        teacher.setFullName(fullName);
        teacher.setEmail(email);
        teacher.setPosition(position);
    }

    public void deleteTeacher(int id) {
        int index = findTeacherIndexById(id);

        if (index == -1) {
            throw new IllegalArgumentException("Викладача з таким ID не знайдено.");
        }

        for (int i = index; i < count - 1; i++) {
            teachers[i] = teachers[i + 1];
        }

        teachers[count - 1] = null;
        count--;
    }

    public int getCount() {
        return count;
    }

    private int findTeacherIndexById(int id) {
        for (int i = 0; i < count; i++) {
            if (teachers[i].getId() == id) {
                return i;
            }
        }

        return -1;
    }
}
