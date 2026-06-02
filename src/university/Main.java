package university;

import java.util.Scanner;

import university.entities.Course;
import university.entities.Enrollment;
import university.entities.Student;
import university.entities.Teacher;
import university.enums.Grade;
import university.enums.StudentStatus;
import university.enums.TeacherPosition;
import university.services.CourseService;
import university.services.EnrollmentService;
import university.services.StudentService;
import university.services.TeacherService;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final TeacherService teacherService = new TeacherService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

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
                        showEnrollmentMenu();
                        break;
                    case "5":
                        showReportsMenu();
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

    private static void showEnrollmentMenu() {
        boolean back = false;

        while (!back) {
            printEnrollmentMenu();
            String choice = readLine("Ваш вибір: ");

            try {
                switch (choice) {
                    case "1":
                        addEnrollment();
                        break;
                    case "2":
                        showEnrollments();
                        break;
                    case "3":
                        setEnrollmentGrade();
                        break;
                    case "4":
                        markEnrollmentAsPaid();
                        break;
                    case "5":
                        showStudentEnrollmentsWithGPA();
                        break;
                    case "6":
                        printStudentTranscript();
                        break;
                    case "0":
                        back = true;
                        break;
                    default:
                        System.out.println("Помилка: оберіть пункт меню від 0 до 6.");
                        break;
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Помилка: " + exception.getMessage());
            }
        }
    }

    private static void printEnrollmentMenu() {
        System.out.println();
        System.out.println("=== Зарахування ===");
        System.out.println("1. Зарахувати студента на курс");
        System.out.println("2. Показати всі зарахування");
        System.out.println("3. Поставити оцінку");
        System.out.println("4. Позначити оплату");
        System.out.println("5. Зарахування студента та GPA");
        System.out.println("6. Транскрипт студента");
        System.out.println("0. Назад");
    }

    private static void addEnrollment() {
        if (studentService.getCount() == 0) {
            throw new IllegalArgumentException("Спочатку додайте хоча б одного студента.");
        }

        if (courseService.getCount() == 0) {
            throw new IllegalArgumentException("Спочатку додайте хоча б один курс.");
        }

        Student student = readStudentById();
        Course course = readCourseById();
        String semester = readLine("Семестр: ");

        Enrollment enrollment = enrollmentService.addEnrollment(student, course, semester);
        System.out.println("Зарахування створено: " + enrollment);
    }

    private static void showEnrollments() {
        Enrollment[] enrollments = enrollmentService.getAllEnrollments();

        if (enrollments.length == 0) {
            System.out.println("Зарахувань немає.");
            return;
        }

        for (int i = 0; i < enrollments.length; i++) {
            System.out.println(enrollments[i]);
        }
    }

    private static void setEnrollmentGrade() {
        showEnrollments();

        if (enrollmentService.getCount() == 0) {
            return;
        }

        int id = readInt("ID зарахування: ");
        Grade grade = readGrade();

        enrollmentService.setGrade(id, grade);
        System.out.println("Оцінку оновлено.");
    }

    private static void markEnrollmentAsPaid() {
        showEnrollments();

        if (enrollmentService.getCount() == 0) {
            return;
        }

        int id = readInt("ID зарахування: ");
        enrollmentService.markAsPaid(id);
        System.out.println("Оплату позначено.");
    }

    private static void showStudentEnrollmentsWithGPA() {
        Student student = readStudentById();
        Enrollment[] enrollments = enrollmentService.getEnrollmentsByStudent(student);

        if (enrollments.length == 0) {
            System.out.println("У студента немає зарахувань.");
            return;
        }

        for (int i = 0; i < enrollments.length; i++) {
            System.out.println(enrollments[i]);
        }

        double gpa = enrollmentService.calculateGPAForStudent(student);
        System.out.println("GPA студента: " + formatDouble(gpa));
    }

    private static void printStudentTranscript() {
        Student student = readStudentById();
        Enrollment[] enrollments = enrollmentService.getEnrollmentsByStudent(student);

        System.out.println();
        System.out.println("=== Транскрипт студента ===");
        System.out.println(student);

        if (enrollments.length == 0) {
            System.out.println("Зарахувань немає.");
            return;
        }

        for (int i = 0; i < enrollments.length; i++) {
            Course course = enrollments[i].getCourse();
            System.out.println(
                    course.getTitle()
                            + " | семестр: " + enrollments[i].getSemester()
                            + " | кредити: " + course.getCredits()
                            + " | оцінка: " + enrollments[i].getGrade()
                            + " | оплачено: " + (enrollments[i].isPaid() ? "так" : "ні")
            );
        }

        double gpa = enrollmentService.calculateGPAForStudent(student);
        System.out.println("GPA: " + formatDouble(gpa));
    }

    private static Grade readGrade() {
        System.out.println("Оцінка:");
        System.out.println("1. A");
        System.out.println("2. B");
        System.out.println("3. C");
        System.out.println("4. D");
        System.out.println("5. F");
        System.out.println("6. NA");

        int choice = readInt("Оберіть оцінку: ");

        switch (choice) {
            case 1:
                return Grade.A;
            case 2:
                return Grade.B;
            case 3:
                return Grade.C;
            case 4:
                return Grade.D;
            case 5:
                return Grade.F;
            case 6:
                return Grade.NA;
            default:
                throw new IllegalArgumentException("Невірна оцінка.");
        }
    }

    private static void showReportsMenu() {
        boolean back = false;

        while (!back) {
            printReportsMenu();
            String choice = readLine("Ваш вибір: ");

            try {
                switch (choice) {
                    case "1":
                        searchStudents();
                        break;
                    case "2":
                        showStudentsByStatus();
                        break;
                    case "3":
                        showStudentsByStudyYear();
                        break;
                    case "4":
                        showStudentsSortedByFullName();
                        break;
                    case "5":
                        showCoursesByTeacher();
                        break;
                    case "6":
                        showCoursesByCredits();
                        break;
                    case "7":
                        showUnpaidEnrollments();
                        break;
                    case "8":
                        showAverageGPAByCourseAndSemester();
                        break;
                    case "9":
                        showTopStudentsByGPA();
                        break;
                    case "0":
                        back = true;
                        break;
                    default:
                        System.out.println("Помилка: оберіть пункт меню від 0 до 9.");
                        break;
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Помилка: " + exception.getMessage());
            }
        }
    }

    private static void printReportsMenu() {
        System.out.println();
        System.out.println("=== Звіти / Пошук ===");
        System.out.println("1. Пошук студента за ПІБ або email");
        System.out.println("2. Студенти за статусом");
        System.out.println("3. Студенти за роком навчання");
        System.out.println("4. Студенти за ПІБ (bubble sort)");
        System.out.println("5. Курси за викладачем");
        System.out.println("6. Курси за кількістю кредитів");
        System.out.println("7. Студенти з неоплаченими курсами");
        System.out.println("8. Середній GPA по курсу/семестру");
        System.out.println("9. Топ-N студентів за GPA");
        System.out.println("0. Назад");
    }

    private static void searchStudents() {
        String query = readLine("Введіть частину ПІБ або email: ");
        Student[] students = studentService.searchStudents(query);
        printStudents(students, "Студентів не знайдено.");
    }

    private static void showStudentsByStatus() {
        StudentStatus status = readStudentStatus();
        Student[] students = studentService.filterByStatus(status);
        printStudents(students, "Студентів з таким статусом не знайдено.");
    }

    private static void showStudentsByStudyYear() {
        int studyYear = readInt("Рік навчання: ");
        Student[] students = studentService.filterByStudyYear(studyYear);
        printStudents(students, "Студентів такого року навчання не знайдено.");
    }

    private static void showStudentsSortedByFullName() {
        Student[] students = studentService.getStudentsSortedByFullName();
        printStudents(students, "Студентів немає.");
    }

    private static void showCoursesByTeacher() {
        Teacher teacher = readTeacherById();
        Course[] courses = courseService.filterByTeacher(teacher);
        printCourses(courses, "Курсів цього викладача не знайдено.");
    }

    private static void showCoursesByCredits() {
        int credits = readInt("Кількість кредитів: ");
        Course[] courses = courseService.filterByCredits(credits);
        printCourses(courses, "Курсів з такою кількістю кредитів не знайдено.");
    }

    private static void showUnpaidEnrollments() {
        Enrollment[] enrollments = enrollmentService.getUnpaidEnrollments();

        if (enrollments.length == 0) {
            System.out.println("Неоплачених курсів немає.");
            return;
        }

        for (int i = 0; i < enrollments.length; i++) {
            System.out.println(enrollments[i]);
        }
    }

    private static void showAverageGPAByCourseAndSemester() {
        Course course = readCourseById();
        String semester = readLine("Семестр: ");
        int gradeCount = enrollmentService.countFinalGradesForCourseAndSemester(course, semester);

        if (gradeCount == 0) {
            System.out.println("Немає виставлених оцінок для цього курсу і семестру.");
            return;
        }

        double averageGPA = enrollmentService.calculateAverageGPAForCourseAndSemester(course, semester);
        System.out.println("Середній GPA: " + formatDouble(averageGPA));
    }

    private static void showTopStudentsByGPA() {
        if (studentService.getCount() == 0) {
            throw new IllegalArgumentException("Студентів немає.");
        }

        int topN = readInt("Скільки студентів показати: ");
        Student[] students = enrollmentService.getTopStudentsByGPA(studentService.getAllStudents(), topN);

        for (int i = 0; i < students.length; i++) {
            double gpa = enrollmentService.calculateGPAForStudent(students[i]);
            System.out.println((i + 1) + ". " + students[i] + ", GPA: " + formatDouble(gpa));
        }
    }

    private static void printStudents(Student[] students, String emptyMessage) {
        if (students.length == 0) {
            System.out.println(emptyMessage);
            return;
        }

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }
    }

    private static void printCourses(Course[] courses, String emptyMessage) {
        if (courses.length == 0) {
            System.out.println(emptyMessage);
            return;
        }

        for (int i = 0; i < courses.length; i++) {
            System.out.println(courses[i]);
        }
    }

    private static Student readStudentById() {
        if (studentService.getCount() == 0) {
            throw new IllegalArgumentException("Студентів немає.");
        }

        showStudents();
        int studentId = readInt("ID студента: ");
        Student student = studentService.findStudentById(studentId);

        if (student == null) {
            throw new IllegalArgumentException("Студента з таким ID не знайдено.");
        }

        return student;
    }

    private static Course readCourseById() {
        if (courseService.getCount() == 0) {
            throw new IllegalArgumentException("Курсів немає.");
        }

        showCourses();
        int courseId = readInt("ID курсу: ");
        Course course = courseService.findCourseById(courseId);

        if (course == null) {
            throw new IllegalArgumentException("Курс з таким ID не знайдено.");
        }

        return course;
    }

    private static Teacher readTeacherById() {
        if (teacherService.getCount() == 0) {
            throw new IllegalArgumentException("Викладачів немає.");
        }

        showTeachers();
        int teacherId = readInt("ID викладача: ");
        Teacher teacher = teacherService.findTeacherById(teacherId);

        if (teacher == null) {
            throw new IllegalArgumentException("Викладача з таким ID не знайдено.");
        }

        return teacher;
    }

    private static String formatDouble(double value) {
        return String.format("%.2f", value);
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
