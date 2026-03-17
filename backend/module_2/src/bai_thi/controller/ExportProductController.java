package module_2.src.bai_thi.controller;

import module_2.src.bai_thi.entity.ExportProduct;
import module_2.src.bai_thi.entity.Product;
import module_2.src.bai_thi.service.ExportProductService;

import java.util.Scanner;

public class ExportProductController {
    private static final byte DISPLAY = 1;
    public static final byte ADD = 2;
    public static final byte DELETE = 3;
    public static final byte UPDATE = 4;

    private final ExportProductService service;

    public ExportProductController() {
        this.service = new ExportProductService();
    }

    public ExportProductController(ExportProductService service) {
        this.service = (ExportProductService) service;
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
        String code = "";
        String name = "";
        String priceString = "";
        double price = 0;
        String quantityString = "";
        int quantity = 0;
        String manufactureCountry = "";
        String importingCountry = "";

        switch (option) {
            case DISPLAY:
                System.out.println("THE INFORMATION STUDENTS");
                try {
                    for (Product product : this.service.getAll()) {
                        System.out.println(product.toString());
                    }
                } catch (RuntimeException runtimeException) {
                    System.out.println(runtimeException.getMessage());
                }
                break;
            case ADD:
                System.out.println("Enter id : ");
                code = scanner.nextLine();
                System.out.println("Enter name : ");
                name = scanner.nextLine();
                System.out.println("Enter price : ");
                priceString = scanner.nextLine();
                price = Double.parseDouble(priceString);
                System.out.println("Enter quantity : ");
                quantityString = scanner.nextLine();
                quantity = Integer.parseInt(quantityString);
                System.out.println("Enter manufactureCountry : ");
                manufactureCountry = scanner.nextLine();
                System.out.println("Enter importingCountry : ");
                importingCountry = scanner.nextLine();

                Product product = new ExportProduct(code, name, price, quantity, manufactureCountry, importingCountry);
                this.service.add(product);
                break;
//            case DELETE:
//                System.out.println("Enter id : ");
//                id = scanner.nextLine();
//                if (id.trim().isEmpty()) {
//                    System.out.println("id is empty");
//                    break;
//                }
//
//                boolean isDeleted = this.service.delete(id);
//                System.out.println(isDeleted ? "Succeed" : "Fail");
//                break;
//            case UPDATE:
//                System.out.println("Enter id : ");
//                id = scanner.nextLine();
//                if (id.trim().isEmpty()) {
//                    System.out.println("id is empty");
//                    break;
//                }
//
//                System.out.println("Update full name or press Enter : ");
//                fullName = scanner.nextLine();
//                System.out.println("Update Address  or press Enter : ");
//                address = scanner.nextLine();
//                System.out.println("Update Score or press Enter : ");
//                score = scanner.nextLine();
//                parseScore = Float.parseFloat(score);
//
//                StudentEntity newStudent = new StudentEntity("", fullName, address, parseScore);
//                StudentEntity currentStudent = this.service.update(id, newStudent);
//                System.out.println(currentStudent != null ? "Succeed" : "Fail");
//                break;


            default:
                System.out.println("is not function");
        }
    }
}
