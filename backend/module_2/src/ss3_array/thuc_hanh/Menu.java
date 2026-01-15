package module_2.src.ss3_array.thuc_hanh;

import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        byte choice = 0;
        while (flag) {
            System.out.println("" +
                    "1. Fahrenheit to Celsius \n" +
                    "2. Celsius to Fahrenheit \n" +
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
                System.out.println("Fahrenheit to Celsius");
                transformFahrenheitToCelsius();
                break;
            case 2:
                System.out.println("Celsius to Fahrenheit");
                transformCelsiusToFahrenheit();
                break;
            case 0:
                System.out.println("Exit");
                break;
            default:
                System.out.println("No choice");
        }
    }

    private static void transformFahrenheitToCelsius() {
        int fahrenheit = Integer.parseInt(scanner.nextLine());
        int celsius = (int) ((5.0 / 9) * (fahrenheit - 32));
        System.out.println("Fahrenheit to Celsius: " + celsius);
    }

    private static void transformCelsiusToFahrenheit() {
        int celsius = Integer.parseInt(scanner.nextLine());
        int fahrenheit = (int) ((9.0 / 5) * celsius + 32);
        System.out.println("Celsius to Fahrenheit: " + fahrenheit);
    }
}
