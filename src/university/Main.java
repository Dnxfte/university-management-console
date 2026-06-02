package university;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMainMenu();
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showSectionMessage("Студенти");
                    break;
                case "2":
                    showSectionMessage("Викладачі");
                    break;
                case "3":
                    showSectionMessage("Курси");
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
}
