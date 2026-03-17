package module_2.src.bai_thi.controller;


import java.util.Scanner;

public class RunApp {
    static ExportProductController exportProductController = new ExportProductController();
    static ImportProductController importProductController = new ImportProductController();

    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        byte choice = 0;
        while (flag) {
            System.out.println("" +
                    "1. Export product \n" +
                    "2. Import product\n" +
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
                System.out.println("Export Product Manager");
                exportProductController.displayMenu();
                break;
            case 2:
                System.out.println("Import Product Manager");
                importProductController.displayMenu();
                break;
            case 0:
                System.out.println("Exit");
                break;
            default:
                System.out.println("No choice");
        }
    }
}
