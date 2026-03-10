package module_2.src.ss8_clean_code.codegym_manager_mvc.controllers;

import module_2.src.ss8_clean_code.codegym_manager_mvc.entities.StudentEntity;
import module_2.src.ss8_clean_code.codegym_manager_mvc.services.IService;
import module_2.src.ss8_clean_code.codegym_manager_mvc.services.StudentService;

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
        String fullName = "";
        String address = "";
        String score = "";
        float parseScore = 0;

        switch (option) {
            case DISPLAY:
                System.out.println("THE INFORMATION STUDENTS");
                try {
                    for (StudentEntity student : this.service.getAll()) {
                        System.out.println(student.toString());
                    }
                } catch (RuntimeException runtimeException) {
                    System.out.println(runtimeException.getMessage());
                }
                break;
            case ADD:
                System.out.println("Enter full name : ");
                fullName = scanner.nextLine();
                System.out.println("Enter address : ");
                address = scanner.nextLine();
                System.out.println("Enter score : ");
                score = scanner.nextLine();
                parseScore = Float.parseFloat(score);

                StudentEntity student = new StudentEntity(StudentService.getNextId(), fullName, address, parseScore);
                this.service.add(student);
                break;
            case DELETE:
                System.out.println("Enter id : ");
                id = scanner.nextLine();
                if (id.trim().isEmpty()) {
                    System.out.println("id is empty");
                    break;
                }

                boolean isDeleted = this.service.delete(id);
                System.out.println(isDeleted ? "Succeed" : "Fail");
                break;
            case UPDATE:
                System.out.println("Enter id : ");
                id = scanner.nextLine();
                if (id.trim().isEmpty()) {
                    System.out.println("id is empty");
                    break;
                }

                System.out.println("Update full name or press Enter : ");
                fullName = scanner.nextLine();
                System.out.println("Update Address  or press Enter : ");
                address = scanner.nextLine();
                System.out.println("Update Score or press Enter : ");
                score = scanner.nextLine();
                parseScore = Float.parseFloat(score);

                StudentEntity newStudent = new StudentEntity("", fullName, address, parseScore);
                StudentEntity currentStudent = this.service.update(id, newStudent);
                System.out.println(currentStudent != null ? "Succeed" : "Fail");
                break;


            default:
                System.out.println("is not function");
        }
    }
}
