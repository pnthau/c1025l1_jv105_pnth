package module_2.src.ss12_java_collection.bai_tap.controllers;

import module_2.src.ss12_java_collection.bai_tap.entities.Product;
import module_2.src.ss12_java_collection.bai_tap.service.IService;
import module_2.src.ss12_java_collection.bai_tap.service.ProductService;
import module_2.src.ss8_clean_code.codegym_manager_mvc.entities.StudentEntity;
import module_2.src.ss8_clean_code.codegym_manager_mvc.services.StudentService;


import java.util.Scanner;

public class ProductController {
    private static final byte DISPLAY = 1;
    public static final byte ADD = 2;
    public static final byte DELETE = 3;
    public static final byte UPDATE = 4;

    private IService<Product, Integer> service;

    public ProductController() {
        this.service = new ProductService();
    }

    public void displayMenu() {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        byte choice = 0;
        while (flag) {
            System.out.println("Choose 1-4 functions : \n" +
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
        String name = "";
        String price = "";
        double parsePrice = 0;
        int parseId = -1;
        switch (option) {
            case DISPLAY:
                System.out.println("THE INFORMATION PRODUCTS");
                for (Product product : this.service.getAll()) {
                    System.out.println("Product" + "{" + product.toString() + "}");
                }
                System.out.println("=========================================");
                break;
            case ADD:
                System.out.println("Enter id : ");
                id = scanner.nextLine();
                parseId = Integer.parseInt(id);
                System.out.println("Enter name : ");
                name = scanner.nextLine();
                System.out.println("Enter price : ");
                price = scanner.nextLine();
                parsePrice = Double.parseDouble(price);

                Product product = new Product(parseId, name, parsePrice, "asdf", "vietnam");
                boolean isAdded = this.service.add(product);
                System.out.println(isAdded ? "Succeed" : "Fail");
                break;
            case DELETE:
                System.out.println("Enter id : ");
                id = scanner.nextLine();
                if (id.isEmpty()) {
                    System.out.println("Id is empty");
                    break;
                }
                parseId = Integer.parseInt(id);
                this.service.delete(parseId);
                break;
            case UPDATE:
                System.out.println("Enter id : ");
                id = scanner.nextLine();
                System.out.println("Enter name : ");
                name = scanner.nextLine();
                System.out.println("Enter price : ");
                price = scanner.nextLine();
                parsePrice = Double.parseDouble(price);

                if (id.isEmpty() || price.isEmpty()) {
                    System.out.println("Id/Price is empty");
                    break;
                }
                Product updatedProduct = new Product(-1, name, parsePrice, "asdf", "vietnam");
                parseId = Integer.parseInt(id);
                this.service.update(parseId, updatedProduct);
                break;
            default:
                System.out.println("is not function");
        }
    }
}
