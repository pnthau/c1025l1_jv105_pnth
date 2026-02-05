package module_2.src.ss8_clean_code.CodeGymManagerMVC.Controller;

import module_2.src.ss8_clean_code.CodeGymManagerMVC.Entity.StudentEntity;
import module_2.src.ss8_clean_code.CodeGymManagerMVC.Service.IService;
import module_2.src.ss8_clean_code.CodeGymManagerMVC.Service.StudentService;

import java.util.Scanner;

public class StudentController {
    private static final byte DISPLAY = 1;
    public static final byte ADD = 2;
    public static final byte DELETE = 3;
    public static final byte UPDATE = 4;

    private final StudentService service;

    public StudentController() {
        this.service = new StudentService();
    }

    public StudentController(IService service) {
        this.service = (StudentService) service;
    }

    public void displayMenu() {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        byte choice = 0;
        while (flag) {
            System.out.println("" +
                    "1. Display \n" +
                    "2. Add \n" +
                    "3. Remove \n" +
                    "4. Update \n" +
                    "0. Exit "
            );
            choice = Byte.parseByte(scanner.nextLine());
            createMenu(choice);
            if (choice == 0) {
                flag = false;
            }
        }
    }

    private void createMenu(byte option) {
        Scanner scanner = new Scanner(System.in);
        String id = "";
        switch (option) {
            case DISPLAY:
                this.service.getAll();
                break;
            case ADD:

                System.out.println("Update full name or press Enter : ");
                String fullName = scanner.nextLine();
                System.out.println("Update Address  or press Enter : ");
                String address = scanner.nextLine();
                System.out.println("Update Score or press Enter : ");
                String score = scanner.nextLine();
                float parseScore = Float.parseFloat(score);

                StudentEntity student = new StudentEntity(StudentService.getNextId(), fullName, address, parseScore);
                this.service.add(student);
                break;
            case DELETE:
                System.out.println("Enter id : ");
                id = scanner.nextLine();
                this.service.delete(id);
                break;
            case UPDATE:
                System.out.println("Enter id : ");
                id = scanner.nextLine();
                this.service.update(id);
                break;
            default:
                System.out.println("is not function");

        }
    }
}
