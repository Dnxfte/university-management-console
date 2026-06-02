package university;

import java.util.Scanner;

import university.entities.Course;
import university.entities.Student;
import university.entities.Teacher;
import university.enums.StudentStatus;
import university.enums.TeacherPosition;
import university.services.CourseService;
import university.services.StudentService;
import university.services.TeacherService;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final TeacherService teacherService = new TeacherService();
    private static final CourseService courseService = new CourseService();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMainMenu();
            String choice = readLine("Ваш вибір: ");

            try {
                switch (choice) {
                    case "1":
                        showStudentMenu();
                        break;
                    case "2":
                        showTeacherMenu();
                        break;
                    case "3":
                        showCourseMenu();
                        break;
                    case "4":
                        showSectionMessage("Зарахування");
                        break;
                    case "5":
                        showSectionMessage("Звіти / Пошук");
                        break;
                    case "0":
                        running = false;
                        System.out.println("Роботу програми завершено.");
                        break;
                    default:
                        System.out.println("Помилка: оберіть пункт меню від 0 до 5.");
                        break;
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Помилка: " + exception.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("=== University Management Console ===");
        System.out.println("1. Студенти");
        System.out.println("2. Викладачі");
        System.out.println("3. Курси");
        System.out.println("4. Зарахування");
        System.out.println("5. Звіти / Пошук");
        System.out.println("0. Вихід");
    }

    private static void showSectionMessage(String sectionName) {
        System.out.println("Розділ \"" + sectionName + "\" буде реалізовано у наступних задачах.");
    }

    private static void showStudentMenu() {
        boolean back = false;

        while (!back) {
            printStudentMenu();
            String choice = readLine("Ваш вибір: ");

            try {
                switch (choice) {
                    case "1":
                        addStudent();
                        break;
                    case "2":
                        showStudents();
                        break;
                    case "3":
                        updateStudent();
                        break;
                    case "4":
                        deleteStudent();
                        break;
                    case "5":
                        changeStudentStatus();
                        break;
                    case "0":
                        back = true;
                        break;
                    default:
                        System.out.println("Помилка: оберіть пункт меню від 0 до 5.");
                        break;
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Помилка: " + exception.getMessage());
            }
        }
    }

    private static void printStudentMenu() {
        System.out.println();
        System.out.println("=== Студенти ===");
        System.out.println("1. Додати студента");
        System.out.println("2. Показати всіх студентів");
        System.out.println("3. Оновити студента");
        System.out.println("4. Видалити студента");
        System.out.println("5. Змінити статус студента");
        System.out.println("0. Назад");
    }

    private static void addStudent() {
        String fullName = readLine("ПІБ: ");
        String email = readLine("Email: ");
        int studyYear = readInt("Рік навчання: ");
        StudentStatus status = readStudentStatus();

        Student student = studentService.addStudent(fullName, email, studyYear, status);
        System.out.println("Студента додано: " + student);
    }

    private static void showStudents() {
        Student[] students = studentService.getAllStudents();

        if (students.length == 0) {
            System.out.println("Студентів немає.");
            return;
        }

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }
    }

    private static void updateStudent() {
        showStudents();

        if (studentService.getCount() == 0) {
            return;
        }

        int id = readInt("ID студента: ");
        String fullName = readLine("Новий ПІБ: ");
        String email = readLine("Новий email: ");
        int studyYear = readInt("Новий рік навчання: ");

        studentService.updateStudent(id, fullName, email, studyYear);
        System.out.println("Дані студента оновлено.");
    }

    private static void deleteStudent() {
        showStudents();

        if (studentService.getCount() == 0) {
            return;
        }

        int id = readInt("ID студента: ");
        studentService.deleteStudent(id);
        System.out.println("Студента видалено.");
    }

    private static void changeStudentStatus() {
        showStudents();

        if (studentService.getCount() == 0) {
            return;
        }

        int id = readInt("ID студента: ");
        StudentStatus status = readStudentStatus();

        studentService.changeStudentStatus(id, status);
        System.out.println("Статус студента оновлено.");
    }

    private static StudentStatus readStudentStatus() {
        System.out.println("Статус:");
        System.out.println("1. ACTIVE");
        System.out.println("2. ON_LEAVE");
        System.out.println("3. EXPELLED");
        System.out.println("4. GRADUATED");

        int choice = readInt("Оберіть статус: ");

        switch (choice) {
            case 1:
                return StudentStatus.ACTIVE;
            case 2:
                return StudentStatus.ON_LEAVE;
            case 3:
                return StudentStatus.EXPELLED;
            case 4:
                return StudentStatus.GRADUATED;
            default:
                throw new IllegalArgumentException("Невірний статус студента.");
        }
    }

    private static void showTeacherMenu() {
        boolean back = false;

        while (!back) {
            printTeacherMenu();
            String choice = readLine("Ваш вибір: ");

            try {
                switch (choice) {
                    case "1":
                        addTeacher();
                        break;
                    case "2":
                        showTeachers();
                        break;
                    case "3":
                        updateTeacher();
                        break;
                    case "4":
                        deleteTeacher();
                        break;
                    case "0":
                        back = true;
                        break;
                    default:
                        System.out.println("Помилка: оберіть пункт меню від 0 до 4.");
                        break;
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Помилка: " + exception.getMessage());
            }
        }
    }

    private static void printTeacherMenu() {
        System.out.println();
        System.out.println("=== Викладачі ===");
        System.out.println("1. Додати викладача");
        System.out.println("2. Показати всіх викладачів");
        System.out.println("3. Оновити викладача");
        System.out.println("4. Видалити викладача");
        System.out.println("0. Назад");
    }

    private static void addTeacher() {
        String fullName = readLine("ПІБ: ");
        String email = readLine("Email: ");
        TeacherPosition position = readTeacherPosition();

        Teacher teacher = teacherService.addTeacher(fullName, email, position);
        System.out.println("Викладача додано: " + teacher);
    }

    private static void showTeachers() {
        Teacher[] teachers = teacherService.getAllTeachers();

        if (teachers.length == 0) {
            System.out.println("Викладачів немає.");
            return;
        }

        for (int i = 0; i < teachers.length; i++) {
            System.out.println(teachers[i]);
        }
    }

    private static void updateTeacher() {
        showTeachers();

        if (teacherService.getCount() == 0) {
            return;
        }

        int id = readInt("ID викладача: ");
        String fullName = readLine("Новий ПІБ: ");
        String email = readLine("Новий email: ");
        TeacherPosition position = readTeacherPosition();

        teacherService.updateTeacher(id, fullName, email, position);
        System.out.println("Дані викладача оновлено.");
    }

    private static void deleteTeacher() {
        showTeachers();

        if (teacherService.getCount() == 0) {
            return;
        }

        int id = readInt("ID викладача: ");
        teacherService.deleteTeacher(id);
        System.out.println("Викладача видалено.");
    }

    private static TeacherPosition readTeacherPosition() {
        System.out.println("Посада:");
        System.out.println("1. ASSISTANT");
        System.out.println("2. LECTURER");
        System.out.println("3. PROFESSOR");

        int choice = readInt("Оберіть посаду: ");

        switch (choice) {
            case 1:
                return TeacherPosition.ASSISTANT;
            case 2:
                return TeacherPosition.LECTURER;
            case 3:
                return TeacherPosition.PROFESSOR;
            default:
                throw new IllegalArgumentException("Невірна посада викладача.");
        }
    }

    private static void showCourseMenu() {
        boolean back = false;

        while (!back) {
            printCourseMenu();
            String choice = readLine("Ваш вибір: ");

            try {
                switch (choice) {
                    case "1":
                        addCourse();
                        break;
                    case "2":
                        showCourses();
                        break;
                    case "3":
                        updateCourse();
                        break;
                    case "4":
                        deleteCourse();
                        break;
                    case "0":
                        back = true;
                        break;
                    default:
                        System.out.println("Помилка: оберіть пункт меню від 0 до 4.");
                        break;
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Помилка: " + exception.getMessage());
            }
        }
    }

    private static void printCourseMenu() {
        System.out.println();
        System.out.println("=== Курси ===");
        System.out.println("1. Додати курс");
        System.out.println("2. Показати всі курси");
        System.out.println("3. Оновити курс");
        System.out.println("4. Видалити курс");
        System.out.println("0. Назад");
    }

    private static void addCourse() {
        if (teacherService.getCount() == 0) {
            throw new IllegalArgumentException("Спочатку додайте хоча б одного викладача.");
        }

        String title = readLine("Назва курсу: ");
        int credits = readInt("Кількість кредитів: ");
        Teacher teacher = readTeacherById();

        Course course = courseService.addCourse(title, credits, teacher);
        System.out.println("Курс додано: " + course);
    }

    private static void showCourses() {
        Course[] courses = courseService.getAllCourses();

        if (courses.length == 0) {
            System.out.println("Курсів немає.");
            return;
        }

        for (int i = 0; i < courses.length; i++) {
            System.out.println(courses[i]);
        }
    }

    private static void updateCourse() {
        showCourses();

        if (courseService.getCount() == 0) {
            return;
        }

        int id = readInt("ID курсу: ");
        String title = readLine("Нова назва курсу: ");
        int credits = readInt("Нова кількість кредитів: ");
        Teacher teacher = readTeacherById();

        courseService.updateCourse(id, title, credits, teacher);
        System.out.println("Дані курсу оновлено.");
    }

    private static void deleteCourse() {
        showCourses();

        if (courseService.getCount() == 0) {
            return;
        }

        int id = readInt("ID курсу: ");
        courseService.deleteCourse(id);
        System.out.println("Курс видалено.");
    }

    private static Teacher readTeacherById() {
        showTeachers();
        int teacherId = readInt("ID викладача: ");
        Teacher teacher = teacherService.findTeacherById(teacherId);

        if (teacher == null) {
            throw new IllegalArgumentException("Викладача з таким ID не знайдено.");
        }

        return teacher;
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int readInt(String prompt) {
        String value = readLine(prompt);

        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Потрібно ввести ціле число.");
        }
    }
}
