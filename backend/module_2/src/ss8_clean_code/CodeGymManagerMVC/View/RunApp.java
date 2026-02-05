package module_2.src.ss8_clean_code.CodeGymManagerMVC.View;

import module_2.src.ss8_clean_code.CodeGymManagerMVC.Controller.StudentController;

import java.util.Scanner;

public class RunApp {
    static StudentController studentController = new StudentController();

    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        byte choice = 0;
        while (flag) {
            System.out.println("" +
                    "1. Student manager \n" +
                    "2. Instructor manager\n" +
                    "0. Exit ");
            choice = Byte.parseByte(scanner.nextLine());
            createMenu(choice);
            if (choice == 0) {
                flag = false;
            }
        }
    }

    private static void createMenu(byte choice) {

        switch (choice) {
            case 1:
                System.out.println("Student Manager");
                studentController.displayMenu();
                break;
            case 2:
                System.out.println("Instructor Manager");
                break;
            case 0:
                System.out.println("Exit");
                break;
            default:
                System.out.println("No choice");
        }
    }
}
