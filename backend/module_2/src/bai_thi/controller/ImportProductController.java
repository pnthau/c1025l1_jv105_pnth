package module_2.src.bai_thi.controller;

import module_2.src.bai_thi.entity.ExportProduct;
import module_2.src.bai_thi.entity.ImportProduct;
import module_2.src.bai_thi.entity.Product;
import module_2.src.bai_thi.service.ExportProductService;
import module_2.src.bai_thi.service.ImportProductService;

import java.util.Scanner;

public class ImportProductController {
    private static final byte DISPLAY = 1;
    public static final byte ADD = 2;
    public static final byte DELETE = 3;
    public static final byte UPDATE = 4;

    private final ImportProductService service;

    public ImportProductController() {
        this.service = new ImportProductService();
    }

    public ImportProductController(ImportProductService service) {
        this.service = (ImportProductService) service;
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
//        String importingCountry = "";
        String importingDutyString = "";
        double importingDuty = 0;

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
                System.out.println("Enter Import Duty : ");
                importingDutyString = scanner.nextLine();
                importingDuty = Double.parseDouble(importingDutyString);

                Product product = new ImportProduct(code, name, price, quantity, manufactureCountry, importingDuty);
                this.service.add(product);
                break;
            default:
                System.out.println("is not function");
        }
    }
}
