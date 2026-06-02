package university.services;

import university.entities.Course;
import university.entities.Teacher;

public class CourseService {
    private Course[] courses;
    private int count;
    private int nextId;

    public CourseService() {
        courses = new Course[100];
        count = 0;
        nextId = 1;
    }

    public Course addCourse(String title, int credits, Teacher teacher) {
        if (count == courses.length) {
            throw new IllegalArgumentException("Немає місця для нового курсу.");
        }

        Course course = new Course(nextId, title, credits, teacher);
        courses[count] = course;
        count++;
        nextId++;

        return course;
    }

    public Course[] getAllCourses() {
        Course[] result = new Course[count];

        for (int i = 0; i < count; i++) {
            result[i] = courses[i];
        }

        return result;
    }

    public Course[] filterByTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Викладач не може бути порожнім.");
        }

        int resultCount = 0;

        for (int i = 0; i < count; i++) {
            if (courses[i].getTeacher().getId() == teacher.getId()) {
                resultCount++;
            }
        }

        Course[] result = new Course[resultCount];
        int resultIndex = 0;

        for (int i = 0; i < count; i++) {
            if (courses[i].getTeacher().getId() == teacher.getId()) {
                result[resultIndex] = courses[i];
                resultIndex++;
            }
        }

        return result;
    }

    public Course[] filterByCredits(int credits) {
        if (credits <= 0) {
            throw new IllegalArgumentException("Кількість кредитів має бути більше 0.");
        }

        int resultCount = 0;

        for (int i = 0; i < count; i++) {
            if (courses[i].getCredits() == credits) {
                resultCount++;
            }
        }

        Course[] result = new Course[resultCount];
        int resultIndex = 0;

        for (int i = 0; i < count; i++) {
            if (courses[i].getCredits() == credits) {
                result[resultIndex] = courses[i];
                resultIndex++;
            }
        }

        return result;
    }

    public Course findCourseById(int id) {
        int index = findCourseIndexById(id);

        if (index == -1) {
            return null;
        }

        return courses[index];
    }

    public void updateCourse(int id, String title, int credits, Teacher teacher) {
        Course course = findCourseById(id);

        if (course == null) {
            throw new IllegalArgumentException("Курс з таким ID не знайдено.");
        }

        course.setTitle(title);
        course.setCredits(credits);
        course.setTeacher(teacher);
    }

    public void deleteCourse(int id) {
        int index = findCourseIndexById(id);

        if (index == -1) {
            throw new IllegalArgumentException("Курс з таким ID не знайдено.");
        }

        for (int i = index; i < count - 1; i++) {
            courses[i] = courses[i + 1];
        }

        courses[count - 1] = null;
        count--;
    }

    public int getCount() {
        return count;
    }

    private int findCourseIndexById(int id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId() == id) {
                return i;
            }
        }

        return -1;
    }
}
