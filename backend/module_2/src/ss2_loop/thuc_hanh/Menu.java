package module_2.src.ss2_loop.thuc_hanh;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        byte choice = 0;
        while (flag) {
            System.out.println("1. Add \n" +
                    "2. Remove \n" +
                    "3. Update \n" +
                    "4. Search \n" +
                    "5. Display \n" +
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
                System.out.println("Add");
                break;
            case 2:
                System.out.println("Remove");
                break;
            case 3:
                System.out.println("Update");
                break;
            case 4:
                System.out.println("Search");
                break;
            case 5:
                System.out.println("Display");
                break;
            case 0:
                System.out.println("Exit");
                break;
            default:
                System.out.println("No choice");
        }
    }
}
